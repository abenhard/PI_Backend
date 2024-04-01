package br.csi.PI_Backend.controller.endereco;


import br.csi.PI_Backend.model.endereco.Estado;

import br.csi.PI_Backend.service.endereco.UFService;
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
    public ResponseEntity<List<Estado>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public Estado uf(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Estado> estados, UriComponentsBuilder uriBuilder)
    {
        for (Estado estado : estados) {
            this.service.cadastrar(estado);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(estados.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(estados);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Estado estado){
        try {
            this.service.atualizar(estado);
            return ResponseEntity.ok().body(estado);
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
