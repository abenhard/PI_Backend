package br.csi.PI_Backend.controller.funcionario;


import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioCadastro;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    private final FuncionarioService service;
    private TokenServiceJWT tokenService;
    public FuncionarioController(FuncionarioService service, TokenServiceJWT tokenService){this.service =service; this.tokenService = tokenService;}

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar(@Valid @RequestBody FuncionarioCadastro funcionarioCadastro, UriComponentsBuilder uriBuilder) {

        if (this.service.findByLogin(funcionarioCadastro.funcionarioDTO().login()) != null) {
            return ResponseEntity.badRequest().body("funcionario já cadastrado!!");
        } else {

            this.service.Cadastrar(funcionarioCadastro);

            return ResponseEntity.created(uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().nome()).toUri())
                    .body("funcionario cadastrado");
        }
    }

    @GetMapping
    public List<Funcionario> getFuncionarios(HttpServletRequest request){
//        String token = request.getHeader("Authorization").replace("Bearer", "");
//        String login = tokenService.getSubject(token);
        return  this.service.findAllFuncionarios();
    }
}
