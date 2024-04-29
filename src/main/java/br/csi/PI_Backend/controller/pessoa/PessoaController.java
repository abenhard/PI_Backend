package br.csi.PI_Backend.controller.pessoa;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.pessoa.PessoaDTO;
import br.csi.PI_Backend.model.pessoa.PessoaEnderecoDTO;
import br.csi.PI_Backend.service.endereco.EnderecoService;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;
    private TokenServiceJWT tokenService;

    public PessoaController(PessoaService pessoaService, EnderecoService enderecoService,TokenServiceJWT tokenService){
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
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
}
