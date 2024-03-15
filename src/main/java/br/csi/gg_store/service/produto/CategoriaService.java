package br.csi.gg_store.service.produto;

import br.csi.gg_store.model.produto.Categoria;
import br.csi.gg_store.model.produto.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){ this.repository = repository;}

    public void  cadastrar(Categoria categoria)
    {
        this.repository.save(categoria);
    }

    public List<Categoria> listar(){
        return this.repository.findAll();
    }

    public Categoria findById(Long id){
        return this.repository.findById(id).get();
    }
    public Categoria getCategoriaPorNome(String nome)
    {
        if(this.repository.getCategoriaByNome(nome)==null)
        {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
            this.repository.save(categoria);

            return categoria;
        }
        else {
            return this.repository.getCategoriaByNome(nome);
        }
    }
    public void atualizar(Categoria categoria){
        Categoria categoriaSalvar = this.repository.getReferenceById(categoria.getId());
        categoriaSalvar.setNome(categoria.getNome());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
