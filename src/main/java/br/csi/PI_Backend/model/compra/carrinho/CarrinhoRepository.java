package br.csi.PI_Backend.model.compra.carrinho;

import br.csi.PI_Backend.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    public Carrinho findCarrinhoByUsuarioCarrinho(Usuario usuario);

}
