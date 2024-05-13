package br.csi.PI_Backend.model.ordem_servico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class OrdemDeServicoDTO{
    String cliente;
    String funcionario;
    String status;
    String tipo_servico;
    String descricao_problema;
    String produto_extra;
    String relatorio_tecnico;
    BigDecimal custo_total;
    Timestamp data_criacao;
    Date data_previsao;
    Date data_entrega;
    String imagem_caminho;
    String localizacao;

    public OrdemDeServicoDTO(String cliente, String funcionario, String status, String tipo_servico, String descricao_problema, Timestamp data_criacao, String imagem_caminho, String localizacao) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.tipo_servico = tipo_servico;
        this.status = status;
        this.descricao_problema = descricao_problema;
        this.data_criacao = data_criacao;
        this.imagem_caminho = imagem_caminho;
        this.localizacao = localizacao;
    }
}