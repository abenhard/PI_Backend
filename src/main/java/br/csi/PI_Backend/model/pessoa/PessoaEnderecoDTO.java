package br.csi.PI_Backend.model.pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaEnderecoDTO {
    private PessoaDTO pessoaDTO;
    private EnderecoDTO enderecoDTO;

}
