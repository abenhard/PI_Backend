package br.csi.PI_Backend.controller.ordem_servico;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.FuncionarioCadastro;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoDTO;
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
    private TokenServiceJWT tokenService;

    public OrdemDeServicoController(OrdemDeServicoService service, TokenServiceJWT tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }
    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar( @Valid @RequestBody OrdemDeServicoDTO ordemDeServicoDTO,
                                             @RequestParam("photos") MultipartFile[] photos,
                                             @RequestParam("localization") String localization,
                                             UriComponentsBuilder uriBuilder) {
        System.out.println("tentou cadastrar Ordem De Serviço");

    }
    @GetMapping("/funcionario")
    public List<OrdemDeServicoDTO> getOrdemDoServicos(HttpServletRequest request){
       String token = request.getHeader("Authorization").replace("Bearer", "");
       String login = tokenService.getSubject(token);


       return service.findOrdemDeServicosByFuncionarioEquals(login);
    }

}
