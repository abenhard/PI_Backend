package br.csi.gg_store.controller.usuario.endereco;

import br.csi.gg_store.infra.security.TokenServiceJWT;
import br.csi.gg_store.model.usuario.endereco.Endereco;
import br.csi.gg_store.model.usuario.endereco.EnderecoDTO;
import br.csi.gg_store.service.usuario.endereco.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService service;
    private final TokenServiceJWT tokenService;
    public EnderecoController(EnderecoService service, TokenServiceJWT tokenService){this.service =service; this.tokenService = tokenService;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping("/{id}")
    public Endereco endereco(@PathVariable Long id){ return this.service.findById(id);}

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listar(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
        String login = tokenService.getSubject(token);
        return ResponseEntity.ok(this.service.listar(login));
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(HttpServletRequest request, @RequestBody @Valid EnderecoDTO enderecosDto)
    {
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
            String login = tokenService.getSubject(token);

            this.service.cadastrar(enderecosDto, login);

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
