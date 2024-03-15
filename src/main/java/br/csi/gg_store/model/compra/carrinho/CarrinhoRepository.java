package br.csi.gg_store.model.compra.carrinho;

import br.csi.gg_store.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    public Carrinho findCarrinhoByUsuarioCarrinho(Usuario usuario);

}
