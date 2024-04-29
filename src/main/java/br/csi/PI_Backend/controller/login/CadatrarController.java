package br.csi.PI_Backend.controller.login;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioCadastro;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cadastrar")
public class CadatrarController {

    private final FuncionarioService service;
    public CadatrarController(FuncionarioService service){this.service =service;}

    @PostMapping
    @Transactional
    public ResponseEntity Cadastrar(@RequestBody @Valid FuncionarioCadastro funcionario)
    {


            if(this.service.findByLogin(funcionario.login())==null){
               this.service.Cadastrar(funcionario);
            }

        return ResponseEntity.ok().body("usuario cadastrado");
    }
}
