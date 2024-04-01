package br.csi.PI_Backend.model.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    public Estado getById(Long id);
    public Estado getEstadoByNome(String nome);
    public Optional<Estado> findById(Long id);
}
