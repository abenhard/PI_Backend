package br.csi.PI_Backend.model.funcionario;

public record FuncionarioCadastro(Long pessoa_id, Long cargo_id, String login, String senha, Boolean ativo) {
}
