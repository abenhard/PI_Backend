package br.csi.gg_store.model.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoDTO{
    private long id;
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal precoBase;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String disponibilidade;
    private Set<String> categorias;
    private String marca;
}
