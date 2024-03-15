package br.csi.gg_store.controller.usuario;

import br.csi.gg_store.infra.security.TokenServiceJWT;
import br.csi.gg_store.model.usuario.DadosUsuario;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;
    private TokenServiceJWT tokenService;
    public UsuarioController(UsuarioService service, TokenServiceJWT tokenService){this.service =service; this.tokenService = tokenService;}

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Usuario> usuarios, UriComponentsBuilder uriBuilder)
    {
        ArrayList<Usuario> u = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if(this.service.findByLogin(usuario.getLogin())!=null){
            }
            else {
                u.add(this.service.cadastrar(usuario));
            }
        }
        URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(u.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(u);
    }
    @GetMapping
    public DadosUsuario getUsuario(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String login = tokenService.getSubject(token);
        DadosUsuario dadosUsuario = this.service.findByLoginDTO(login);

        return dadosUsuario;
    }
    @GetMapping("/{id}")
    public DadosUsuario getById(@PathVariable Long id){return this.service.findUsuario(id);}
    @GetMapping("/todosUsuarios")
    public List<DadosUsuario> getAll(){
        return this.service.findAllUsuarios();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Usuario usuario){
        try {
            this.service.atualizar(usuario);
            return ResponseEntity.ok().body(usuario);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Usuario");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Usuario Deletado com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

}
