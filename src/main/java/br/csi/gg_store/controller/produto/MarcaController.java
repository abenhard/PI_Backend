package br.csi.gg_store.controller.produto;


import br.csi.gg_store.model.produto.Marca;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.produto.MarcaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService service;
    public MarcaController(MarcaService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<Marca>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public Marca marca(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Marca> marcas, UriComponentsBuilder uriBuilder)
    {
        for (Marca marca : marcas) {
            this.service.cadastrar(marca);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(marcas.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(marcas);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Marca marca){
        try {
            this.service.atualizar(marca);
            return ResponseEntity.ok().body(marca);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Marca");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Marca Deletada com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
