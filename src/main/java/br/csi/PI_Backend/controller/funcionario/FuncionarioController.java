package br.csi.PI_Backend.controller.funcionario;


import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.DadosFuncionario;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;
    private TokenServiceJWT tokenService;
    public FuncionarioController(FuncionarioService service, TokenServiceJWT tokenService){this.service =service; this.tokenService = tokenService;}

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity Cadastrar(@RequestBody @Valid DadosFuncionario funcionario, UriComponentsBuilder uriBuilder)
    {

            if(this.service.findByLogin(funcionario.getLogin())!=null){
                return ResponseEntity.badRequest().body("funcionario já cadastrado!!");
            }
            else {
               this.service.Cadastrar(funcionario);
            }

        return ResponseEntity.ok().body("funcionario cadastrado");
    }
}
