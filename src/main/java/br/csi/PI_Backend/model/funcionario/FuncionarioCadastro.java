package br.csi.PI_Backend.model.funcionario;

import br.csi.PI_Backend.model.pessoa.PessoaEnderecoDTO;

public record FuncionarioCadastro(PessoaEnderecoDTO pessoaEnderecoDTO, FuncionarioDTO funcionarioDTO) {
}
