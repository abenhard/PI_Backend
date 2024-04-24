package br.csi.PI_Backend.model.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    public Cidade getById(Long id);
    Cidade findByNomeAndEstadoNome(String nome, String estadoNome);
    public Optional<Cidade> findById(Long id);
}
