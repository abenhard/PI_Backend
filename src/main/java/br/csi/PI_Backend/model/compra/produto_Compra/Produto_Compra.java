package br.csi.PI_Backend.model.compra.produto_Compra;

import br.csi.PI_Backend.model.produto.Produto;
import br.csi.PI_Backend.model.compra.Compra;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="produto_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto_Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idcompra")
    private Compra compra;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    @Min(value = 1, message = "Quantidade deve ser maior ou iqual 1")
    private Integer quantidade = 0;
}
