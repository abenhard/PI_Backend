package br.csi.PI_Backend.model.compra;

import br.csi.PI_Backend.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    public Set<Compra> findCompraByUsuarioVenda(Usuario usuario);

    public Compra getCompraById(Long id);
    public Compra findCompraByUsuarioVendaAndId(Usuario usuario, Long id);
}
