package br.csi.PI_Backend.model.funcionario;

import br.csi.PI_Backend.model.pessoa.Pessoa;

public record PessoaFuncionario(Pessoa pessoa, Funcionario funcionario) {
}
