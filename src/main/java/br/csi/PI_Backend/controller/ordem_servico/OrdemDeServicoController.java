package br.csi.PI_Backend.controller.ordem_servico;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.ordem_servico.*;
import br.csi.PI_Backend.service.ordem_servico.ImageService;
import br.csi.PI_Backend.service.ordem_servico.OrdemDeServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("ordem")
public class OrdemDeServicoController {
    private final OrdemDeServicoService service;
    private final ImageService imageService;
    private final TokenServiceJWT tokenService;

    public OrdemDeServicoController(OrdemDeServicoService service, ImageService imageService, TokenServiceJWT tokenService) {
        this.service = service;
        this.imageService = imageService;
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
    public ResponseEntity<String> cadastrar(@Valid OrdemDeServicoDTO ordemDeServicoDTO,
                                            @RequestParam(value = "fotos", required = false) MultipartFile[] fotos,
                                            HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();

        try {
            String login = tokenService.getSubject(token);
            ordemDeServicoDTO.setFuncionarioLogin(login);
            System.out.println("Cadastro tecnico:" + login);
            this.service.cadastrarOrdemTecnico(ordemDeServicoDTO, fotos);
            return ResponseEntity.ok().body("Ordem de serviço cadastrada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();  // Log the stack trace for debugging
            return ResponseEntity.badRequest().body("Ocorreu um problema com o cadastro da Ordem de serviço");
        }
    }

    @PutMapping("/alterarOrdem")
    @Transactional
    public ResponseEntity<String> alterar(@Valid OrdemDeServicoExibicaoDTO ordemDeServicoDTO,
                                          @RequestParam(value = "fotos", required = false) MultipartFile[] fotos,
                                          HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();

        try {
            this.service.alterar(ordemDeServicoDTO, fotos);
            return ResponseEntity.ok().body("Ordem de serviço cadastrada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();  // Log the stack trace for debugging
            return ResponseEntity.badRequest().body("Ocorreu um problema com o cadastro da Ordem de serviço");
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdemDeServicoExibicaoDTO>> getOrdensDeServico(HttpServletRequest request) {
        return ResponseEntity.ok(service.getOrdensDeServico());
    }

    @GetMapping("/funcionario/tecnico")
    public ResponseEntity<List<OrdemDeServicoExibicaoDTO>> getOrdemDeServicos(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();
        try {
            String login = tokenService.getSubject(token);
            List<OrdemDeServicoExibicaoDTO> ordens = service.getOrdensDeServicoByFuncionarioEquals(login);
            return ResponseEntity.ok(ordens);
        } catch (RuntimeException e) {
            e.printStackTrace();  // Log the stack trace for debugging
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }

    @GetMapping("/funcionario/{login}")
    public List<OrdemDeServicoExibicaoDTO> getOrdemDoServicos(@PathVariable String login) {
        return service.getOrdensDeServicoByFuncionarioEquals(login);
    }
}
