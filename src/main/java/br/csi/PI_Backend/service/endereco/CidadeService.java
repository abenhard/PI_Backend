package br.csi.PI_Backend.service.endereco;

import br.csi.PI_Backend.model.endereco.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    private final CidadeRepository repository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository repository, EstadoRepository estadoRepository){
        this.repository = repository;
        this.estadoRepository = estadoRepository;
    }

    public void salvar(String nome, Long iduf)
    {
        Estado estado = estadoRepository.getById(iduf);
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setEstado(estado);
        this.repository.save(cidade);
    }

    public List<CidadeDTO> listar(){
        return this.repository.findAll().stream().map(CidadeDTO::new).toList();
    }
    public Cidade findById(Long id){
        return this.repository.findById(id).get();
    }
    public void atualizar(Cidade cidade){
        Cidade cidadeAtualizar = this.repository.getReferenceById(cidade.getId());
        cidadeAtualizar.setNome(cidade.getNome());
        cidadeAtualizar.setEstado(cidade.getEstado());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
    @Transactional
    public Cidade getOrCreateCidade(String nome, String estado) {
        Optional<Cidade> existingCidade = Optional.ofNullable(this.repository.findByNomeAndEstadoNome(nome, estado));
        if (existingCidade.isPresent()) {
            return existingCidade.get();
        } else {
            Cidade newCidade = new Cidade();
            newCidade.setNome(nome);
            newCidade.setEstado(estadoRepository.getEstadoByNome(estado));
            return this.repository.save(newCidade);
        }
    }
}
