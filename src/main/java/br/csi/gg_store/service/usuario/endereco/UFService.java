package br.csi.gg_store.service.usuario.endereco;

import br.csi.gg_store.model.usuario.endereco.UF;
import br.csi.gg_store.model.usuario.endereco.UFRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UFService {
    private final UFRepository repository;

    public UFService(UFRepository repository){ this.repository = repository;}

    public void  cadastrar(UF uf)
    {
        this.repository.save(uf);
    }

    public List<UF> listar(){
        return this.repository.findAll();
    }

    public UF findById(Long id){
        return this.repository.findById(id).get();
    }
    public UF getUfPorNome(String nome)
    {
        return this.repository.getUFByNome(nome);
    }
    public void atualizar(UF uf){
        UF ufSalvar = this.repository.getReferenceById(uf.getId());
        ufSalvar.setNome(uf.getNome());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

}
