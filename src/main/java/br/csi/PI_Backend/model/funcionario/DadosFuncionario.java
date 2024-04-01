package br.csi.PI_Backend.model.funcionario;

import java.math.BigDecimal;
import java.sql.Date;

public record DadosFuncionario(String cpf, Date data_de_entrada, String cargo, String login, String senha, BigDecimal salario) {
}
