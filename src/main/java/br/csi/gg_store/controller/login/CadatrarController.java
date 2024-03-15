package br.csi.gg_store.controller.login;

import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.usuario.UsuarioService;
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

    private final UsuarioService service;
    public CadatrarController(UsuarioService service){this.service =service;}

    @PostMapping
    @Transactional
    public ResponseEntity Cadastrar(@RequestBody @Valid Usuario usuario)
    {

            if(this.service.findByLogin(usuario.getLogin())!=null){
            }
            else {
               this.service.cadastrar(usuario);
            }

        return ResponseEntity.ok().body("usuario cadastrado");
    }
}
