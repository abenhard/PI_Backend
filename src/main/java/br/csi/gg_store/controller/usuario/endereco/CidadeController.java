package br.csi.gg_store.controller.usuario.endereco;

import br.csi.gg_store.model.usuario.endereco.Cidade;
import br.csi.gg_store.model.usuario.endereco.CidadeDTO;
import br.csi.gg_store.service.usuario.endereco.CidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeService service;
    public CidadeController(CidadeService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> findAll(){
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity cidade(@PathVariable Long id){ return ResponseEntity.ok(this.service.findById(id));}

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<CidadeDTO> cidades, UriComponentsBuilder uriBuilder)
    {
        for (CidadeDTO cidade : cidades) {
            this.service.salvar(cidade.nome(), cidade.iduf());
        }
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Cidade cidade){
        try {
            this.service.atualizar(cidade);
            return ResponseEntity.ok().body(cidade);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Cidade");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Cidade Deletada com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
