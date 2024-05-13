package br.csi.PI_Backend.controller.pessoa;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;

import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.pessoa.PessoaEnderecoDTO;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private TokenServiceJWT tokenService;

    public PessoaController(PessoaService pessoaService, TokenServiceJWT tokenService){
        this.pessoaService = pessoaService;
        this.tokenService = tokenService;}

    @PostMapping
    @Transactional
    public ResponseEntity Cadastrar(@RequestBody @Valid PessoaEnderecoDTO pessoaCadastro)
    {

        if(this.pessoaService.getByCpf(pessoaCadastro.getPessoaDTO().cpf()) != null){
            return ResponseEntity.badRequest().body("Cliente já cadastrado!!");
        }
        else {
            this.pessoaService.Cadastrar(pessoaCadastro.getPessoaDTO() ,pessoaCadastro.getEnderecoDTO());
        }

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso");
    }
    @GetMapping("/{cpf}")
    public Pessoa CPF(@PathVariable String cpf){
        return this.pessoaService.getByCpf(cpf);
    }
}
