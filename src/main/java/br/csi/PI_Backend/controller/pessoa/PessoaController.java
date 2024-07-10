package br.csi.PI_Backend.controller.pessoa;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;

import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.pessoa.PessoaEnderecoDTO;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

        if(this.pessoaService.jaExiste(pessoaCadastro.getPessoaDTO())){
            this.pessoaService.Cadastrar(pessoaCadastro.getPessoaDTO() ,pessoaCadastro.getEnderecoDTO());
            System.out.println(pessoaCadastro.getPessoaDTO().cpf());
        }
        else {
            System.out.println("Nome: " + pessoaCadastro.getPessoaDTO().nome());
            System.out.println("Cpf: " + pessoaCadastro.getPessoaDTO().nome());
            return ResponseEntity.badRequest().body("Cliente já cadastrado!!");
        }

        return ResponseEntity.ok().body("Cliente cadastrado com sucesso");
    }
    @GetMapping
    public List<Pessoa> getPessoas(){
        return this.pessoaService.getAllPessoa();
    }
    @GetMapping("/clientes")
    public List<Pessoa> getClientes(){
        return this.pessoaService.getClientesOnly();
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> getPessoaByCpf(@PathVariable String cpf) {
        Pessoa pessoa = pessoaService.getByCpf(cpf);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{cpfOrLogin}")
    public List<Pessoa> getPessoaByCpfOrLogin(@PathVariable String cpfOrLogin) {
        System.out.println("Buscando Cliente : " + cpfOrLogin);
        return this.pessoaService.findByCpfOrNome(cpfOrLogin);
    }

    @PutMapping
    public ResponseEntity updatePessoa(@Valid @RequestBody Pessoa pessoa){
        System.out.println("Atualizando Cadastro");
        if(this.pessoaService.Atualizar(pessoa)){
            System.out.println("SUCESSO");
            return ResponseEntity.ok("Cadastro Atualizado com sucesso");

        }
        else{
            System.out.println("FALHOU");
            return ResponseEntity.badRequest().body("Falha ao atualizado cadastro");

        }

    }


}
