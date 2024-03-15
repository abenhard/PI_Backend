package br.csi.gg_store.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto getById(Long id);
    List<Produto> getProdutoByNome(String nome);
    Optional<Produto> findById(Long id);

}
