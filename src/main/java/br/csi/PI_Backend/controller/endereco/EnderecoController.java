package br.csi.PI_Backend.controller.endereco;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.endereco.Endereco;
import br.csi.PI_Backend.model.endereco.EnderecoDTO;
import br.csi.PI_Backend.service.endereco.EnderecoService;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService service;
    private final PessoaService pessoaService;
    private final TokenServiceJWT tokenService;
    public EnderecoController(EnderecoService service, TokenServiceJWT tokenService, PessoaService pessoaService){
        this.service =service;
        this.tokenService = tokenService;
        this.pessoaService = pessoaService;
    }

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping("/{id}")
    public Endereco endereco(@PathVariable Long id){ return this.service.findById(id);}



    @PostMapping
    @Transactional
    public ResponseEntity salvar(HttpServletRequest request, @RequestBody @Valid EnderecoDTO enderecosDto)
    {
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
            String login = tokenService.getSubject(token);

            this.service.cadastrar(enderecosDto, pessoaService.getByEmail(login));

            return ResponseEntity.ok("Endereço cadastrado com Sucesso");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao Cadastrar Endereço");
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Endereco endereco){
        try {
            this.service.atualizar(endereco);
            return ResponseEntity.ok().body(endereco);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Endereco");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Endereco Deletada com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
