package br.csi.gg_store.model.compra.produto_carrinho;


import br.csi.gg_store.model.produto.Disponibilidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Produto_CarrinhoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCarrinho;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Disponibilidade disponibilidade;

    public Produto_CarrinhoDTO(){}
    public Produto_CarrinhoDTO(Long idProduto, Integer quantidade, String disponibilidade){
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.disponibilidade = Disponibilidade.valueOf(disponibilidade);
    }

}
