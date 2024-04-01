package br.csi.PI_Backend.service.endereco;

import br.csi.PI_Backend.model.endereco.Estado;
import br.csi.PI_Backend.model.endereco.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UFService {
    private final EstadoRepository repository;

    public UFService(EstadoRepository repository){ this.repository = repository;}

    public void  cadastrar(Estado estado)
    {
        this.repository.save(estado);
    }

    public List<Estado> listar(){
        return this.repository.findAll();
    }

    public Estado findById(Long id){
        return this.repository.findById(id).get();
    }
    public Estado getUfPorNome(String nome)
    {
        return this.repository.getEstadoByNome(nome);
    }
    public void atualizar(Estado estado){
        Estado estadoSalvar = this.repository.getReferenceById(estado.getId());
        estadoSalvar.setNome(estado.getNome());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

}
