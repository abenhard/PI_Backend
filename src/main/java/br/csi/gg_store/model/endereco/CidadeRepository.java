package br.csi.gg_store.model.usuario.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    public Cidade getById(Long id);
    public Cidade getCidadeByNomeOrUf(String nome, UF uf);
    public Optional<Cidade> findById(Long id);
}
