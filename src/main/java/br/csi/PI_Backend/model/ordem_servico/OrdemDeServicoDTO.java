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

    public OrdemDeServicoDTO(String clienteCPF, String funcionarioLogin, String status, String tipo_servico, String descricao_problema, String produto_extra, String relatorio_tecnico, BigDecimal custo_total, Timestamp data_criacao, Date data_previsao, Date data_entrega, String imagem_caminho, String localizacao) {
        this.clienteCPF = clienteCPF;
        this.funcionarioLogin = funcionarioLogin;
        this.status = status;
        this.tipo_servico = tipo_servico;
        this.descricao_problema = descricao_problema;
        this.produto_extra = produto_extra;
        this.relatorio_tecnico = relatorio_tecnico;
        this.custo_total = custo_total;
        this.data_criacao = data_criacao;
        this.data_previsao = data_previsao;
        this.data_entrega = data_entrega;
        this.imagem_caminho = imagem_caminho;
        this.localizacao = localizacao;
    }

    public OrdemDeServicoDTO(String clienteCPF, String funcionarioLogin, String status, String tipo_servico, String descricao_problema, Timestamp data_criacao, String imagem_caminho, String localizacao) {
        this.clienteCPF = clienteCPF;
        this.funcionarioLogin = funcionarioLogin;
        this.tipo_servico = tipo_servico;
        this.status = status;
        this.descricao_problema = descricao_problema;
        this.data_criacao = data_criacao;
        this.imagem_caminho = imagem_caminho;
        this.localizacao = localizacao;
    }

    public OrdemDeServicoDTO(String clienteCPF, String funcionariologin, String status, String tipo_servico, String descricao_problema, Timestamp data_criacao) {
        this.clienteCPF = clienteCPF;
        this.funcionarioLogin = funcionariologin;
        this.status = status;
        this.tipo_servico = tipo_servico;
        this.descricao_problema = descricao_problema;
        this.data_criacao = data_criacao;
    }
}