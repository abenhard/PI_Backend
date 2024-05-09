package br.csi.PI_Backend.model.pessoa;

import br.csi.PI_Backend.model.endereco.EnderecoDTO;

public class PessoaEnderecoDTO {
    private PessoaDTO pessoaDTO;
    private EnderecoDTO enderecoDTO;

    public PessoaDTO getPessoaDTO() {
        return pessoaDTO;
    }

    public void setPessoaDTO(PessoaDTO pessoaDTO) {
        this.pessoaDTO = pessoaDTO;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
