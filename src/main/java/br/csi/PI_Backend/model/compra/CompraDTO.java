package br.csi.PI_Backend.model.compra;

import br.csi.PI_Backend.model.compra.produto_Compra.Produto_CompraDTO;
import br.csi.PI_Backend.model.endereco.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CompraDTO {
    private Long id;
    private String nomeUsuario;
    private EnderecoDTO enderecoDTO;
    private StatusCompra statusCompra;
    private Set<Produto_CompraDTO> produtoCompraDTOSet;

}
