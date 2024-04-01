package br.csi.PI_Backend.model.compra.produto_carrinho;

import br.csi.PI_Backend.model.produto.Produto;
import br.csi.PI_Backend.model.compra.carrinho.Carrinho;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="produto_carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto_Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idcarrinho")
    private Carrinho carrinho;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    @Min(value = 1, message = "Quantidade deve ser maior ou iqual 1")
    private Integer quantidade = 0;

}
