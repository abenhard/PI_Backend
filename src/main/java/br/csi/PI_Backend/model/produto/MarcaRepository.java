package br.csi.PI_Backend.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    public Marca getById(Long id);
    public Marca findMarcaByNomeContaining(String nome);
    public Optional<Marca> findById(Long id);
}
