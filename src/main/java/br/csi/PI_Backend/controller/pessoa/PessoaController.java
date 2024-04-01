package br.csi.PI_Backend.controller.pessoa;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private TokenServiceJWT tokenService;

    public PessoaController(PessoaService pessoaService, TokenServiceJWT tokenService){this.pessoaService=pessoaService; this.tokenService=tokenService;}


}
