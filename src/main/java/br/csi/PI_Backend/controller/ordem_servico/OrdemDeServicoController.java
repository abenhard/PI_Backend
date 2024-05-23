package br.csi.PI_Backend.controller.ordem_servico;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoAtendenteDTO;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoDTO;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoTecnicoDTO;
import br.csi.PI_Backend.service.ordem_servico.OrdemDeServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("ordem")
public class OrdemDeServicoController {
    private final OrdemDeServicoService service;
    private final TokenServiceJWT tokenService;

    public OrdemDeServicoController(OrdemDeServicoService service, TokenServiceJWT tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/cadastroPorAtendente")
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody OrdemDeServicoAtendenteDTO ordemDeServicoDTO,
                                            UriComponentsBuilder uriBuilder) {
        if (this.service.cadastrarOrdemAtendente(ordemDeServicoDTO)) {
            return ResponseEntity.ok().body("Ordem de serviço cadastrada com sucesso");
        }
        return ResponseEntity.badRequest().body("Erro ao cadastrar Ordem de serviço");
    }

    @PostMapping("/cadastroPorTecnico")
    @Transactional
    public ResponseEntity<String> cadastrar(@Valid OrdemDeServicoTecnicoDTO ordemDeServicoDTO,
                                            @RequestParam("fotos") MultipartFile[] fotos,
                                            HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();

        try {
            String login = tokenService.getSubject(token);
            ordemDeServicoDTO.setFuncionariologin(login);
            System.out.println("Cadastro tecnico:" + login);
            this.service.cadastrarOrdemTecnico(ordemDeServicoDTO, fotos);
            return ResponseEntity.ok().body("Ordem de serviço cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um problema com o cadastro da Ordem de serviço");
        }
    }

    @GetMapping("/funcionario/tecnico")
    public ResponseEntity<List<OrdemDeServicoDTO>> getOrdemDeServicosByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();
        try {
            String login = tokenService.getSubject(token);
            List<OrdemDeServicoDTO> ordens = service.findOrdensDeServicoByFuncionarioEquals(login);
            return ResponseEntity.ok(ordens);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }


    @GetMapping("/funcionario/{login}")
    public List<OrdemDeServicoDTO> getOrdemDoServicos(@PathVariable String login) {
        return service.findOrdensDeServicoByFuncionarioEquals(login);
    }
}
