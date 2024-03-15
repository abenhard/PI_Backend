package br.csi.gg_store.model.compra.produto_carrinho;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Produto_CarrinhoRepository extends JpaRepository<Produto_Carrinho, Long> {
    Optional<Produto_Carrinho> findProduto_CarrinhoByCarrinhoIdAndProdutoId(Long carrinhoId, Long produtoId);
}
