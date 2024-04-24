package br.csi.PI_Backend.model.funcionario;

import br.csi.PI_Backend.model.pessoa.Pessoa;

import java.math.BigDecimal;
import java.sql.Date;

public record DadosFuncionario(String nome, String email, String telefone, String whatsapp, String cpf, Date data_de_entrada, String cargo, String login, String senha) {
    public String getLogin(){
        return login;
    }
}
