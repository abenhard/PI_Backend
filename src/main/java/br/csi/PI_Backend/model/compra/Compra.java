package br.csi.PI_Backend.model.compra;

import br.csi.PI_Backend.model.endereco.Endereco;
import br.csi.PI_Backend.model.usuario.Usuario;
import br.csi.PI_Backend.model.compra.produto_Compra.Produto_Compra;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="precototal")
    private BigDecimal precoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name= "status")
    private StatusCompra status;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "idusuario")
    private Usuario usuarioVenda;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "idendereco")
    private Endereco enderecoCompra;

    @OneToMany(mappedBy = "compra")
    @JsonIgnore
    private Set<Produto_Compra> produtosCompra = new HashSet<>();
}
