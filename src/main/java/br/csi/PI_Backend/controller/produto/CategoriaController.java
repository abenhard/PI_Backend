package br.csi.PI_Backend.controller.produto;


import br.csi.PI_Backend.model.produto.Categoria;
import br.csi.PI_Backend.service.produto.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Categoria> categorias, UriComponentsBuilder uriBuilder)
    {
        for (Categoria categoria : categorias) {
            this.service.cadastrar(categoria);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(categorias.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(categorias);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Categoria categoria){
        try {
            this.service.atualizar(categoria);
            return ResponseEntity.ok().body(categoria);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Categoria");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Categoria Deletado com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
