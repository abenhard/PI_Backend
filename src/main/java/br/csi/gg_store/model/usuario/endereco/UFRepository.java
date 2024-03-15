package br.csi.gg_store.model.usuario.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UFRepository extends JpaRepository<UF, Long> {
    public UF getById(Long id);
    public UF getUFByNome(String nome);
    public Optional<UF> findById(Long id);
}
