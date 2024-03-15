package br.csi.gg_store.controller.usuario.endereco;


import br.csi.gg_store.model.usuario.endereco.UF;

import br.csi.gg_store.service.usuario.endereco.UFService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/uf")
public class UFController {
    private final UFService service;
    public UFController(UFService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<UF>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public UF uf(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<UF> ufs, UriComponentsBuilder uriBuilder)
    {
        for (UF uf : ufs) {
            this.service.cadastrar(uf);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(ufs.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(ufs);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UF uf){
        try {
            this.service.atualizar(uf);
            return ResponseEntity.ok().body(uf);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar UF");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("UF Deletada com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
