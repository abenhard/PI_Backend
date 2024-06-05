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
    private Long id;
    private String clienteCPF;
    private String funcionarioLogin;
    private String status;
    private String tipo_servico;
    private String descricao_problema;
    private String produto_extra;
    private String relatorio_tecnico;
    private BigDecimal custo_total;
    private Timestamp data_criacao;
    private Date data_previsao;
    private Date data_entrega;
    private String imagem_caminho;
    private String localizacao;

    public OrdemDeServicoDTO(String clienteCPF, String funcionarioLogin, String status, String tipo_servico, String descricao_problema, String relatorio_tecnico, String produto_extra, Date data_previsao, String localizacao) {
        this.clienteCPF = clienteCPF;
        this.funcionarioLogin = funcionarioLogin;
        this.status = status;
        this.tipo_servico = tipo_servico;
        this.descricao_problema = descricao_problema;
        this.relatorio_tecnico = relatorio_tecnico;
        this.produto_extra = produto_extra;
        this.data_previsao = data_previsao;
        this.localizacao = localizacao;
    }
}