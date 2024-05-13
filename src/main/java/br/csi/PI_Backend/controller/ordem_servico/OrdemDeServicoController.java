package br.csi.PI_Backend.controller.ordem_servico;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoDTO;
import br.csi.PI_Backend.service.ordem_servico.OrdemDeServicoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ordem")
public class OrdemDeServicoController {
    private final OrdemDeServicoService ordemDeServicoService;
    private TokenServiceJWT tokenService;

    public OrdemDeServicoController(OrdemDeServicoService ordemDeServicoService, TokenServiceJWT tokenService) {
        this.ordemDeServicoService = ordemDeServicoService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<OrdemDeServicoDTO> getOrdemDoServicos(HttpServletRequest request){
       String token = request.getHeader("Authorization").replace("Bearer", "");
       String login = tokenService.getSubject(token);


       return ordemDeServicoService.findOrdemDeServicosByFuncionarioEquals(login);
    }

}
