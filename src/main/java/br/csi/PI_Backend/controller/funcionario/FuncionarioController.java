package br.csi.PI_Backend.controller.funcionario;


import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioCadastro;
import br.csi.PI_Backend.model.funcionario.FuncionarioDTO;
import br.csi.PI_Backend.model.funcionario.PessoaFuncionario;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import br.csi.PI_Backend.service.pessoa.PessoaService;
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
    private final PessoaService pessoaService;
    private TokenServiceJWT tokenService;
    public FuncionarioController(FuncionarioService service, PessoaService pessoaService, TokenServiceJWT tokenService){
        this.service =service;
        this.tokenService = tokenService;
        this.pessoaService = pessoaService;
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar(@Valid @RequestBody FuncionarioCadastro funcionarioCadastro, UriComponentsBuilder uriBuilder) {

        if (!this.pessoaService.jaExiste(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO())) {
            return ResponseEntity.badRequest().body("funcionario já cadastrado!!");
        } else {

            this.service.Cadastrar(funcionarioCadastro);

            return ResponseEntity.created(uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().nome()).toUri())
                    .body("funcionario cadastrado");
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<String> alterar(@Valid @RequestBody FuncionarioCadastro funcionarioCadastro){

        System.out.println("Tentando ataulaizar Cadastro");
        try{
            this.service.Alterar(funcionarioCadastro);
            return ResponseEntity.ok("Cadastro Alterado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Falha ao atualizar o cadastro");
        }
    }
    @GetMapping("/tecnicos")
    public List<Funcionario> getTecnicos(HttpServletRequest request){
        return  this.service.findTecnicos();
    }
    @GetMapping
    public List<PessoaFuncionario> getFuncionarios(){
        return  this.service.findAllFuncionarios();
    }
}
