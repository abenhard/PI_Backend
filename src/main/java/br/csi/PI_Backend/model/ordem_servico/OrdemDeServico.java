package br.csi.PI_Backend.model.ordem_servico;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;

@Entity(name ="OrdemServico")
@Table(name ="ordens_de_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotNull
    @NotBlank
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull
    @NotBlank
    @Column(name = "status")
    private String status;

    @NotNull
    @NotBlank
    @Column(name = "tipo_de_servico")
    private String tipo_servico;

    @NotNull
    @NotBlank
    @Column(name = "descricao_problema")
    private String descricao_problema;

    @Column(name = "produto_extra")
    private String produto_extra;

    @Column(name = "relatorio_tecnico")
    private String relatorio_tecnico;

    @Column(name = "custo_total")
    private BigDecimal custo_total;

    @NotNull
    @NotBlank
    @Column(name = "data_criacao")
    private Timestamp data_criacao;

    @Column(name = "data_previsao")
    private Date data_previsao;

    @Column(name = "data_entrega")
    private Date data_entrega;

    @NotNull
    @NotBlank
    @Column(name = "imagem_caminho")
    private String imagem_caminho;

    @NotNull
    @NotBlank
    @Column(name = "localizacao")
    private String localizacao;


    public OrdemDeServico(Pessoa pessoa, Funcionario funcionario, String status, String tipo_servico, String descricao_problema, String produto_extra, String relatorio_tecnico, BigDecimal custo_total, Timestamp data_criacao, Date data_previsao, String imagem_caminho, String localizacao) {
        this.pessoa = pessoa;
        this.funcionario = funcionario;
        this.status = status;
        this.tipo_servico = tipo_servico;
        this.descricao_problema = descricao_problema;
        this.produto_extra = produto_extra;
        this.relatorio_tecnico = relatorio_tecnico;
        this.custo_total = custo_total;
        this.data_criacao = data_criacao;
        this.data_previsao = data_previsao;
        this.imagem_caminho = imagem_caminho;
        this.localizacao = localizacao;
    }

    public OrdemDeServico(Pessoa pessoa, Funcionario funcionario, String status, String tipo_servico, String descricao_problema) {
        this.pessoa = pessoa;
        this.funcionario = funcionario;
        this.status = status;
        this.tipo_servico = tipo_servico;
        this.descricao_problema = descricao_problema;
    }

}


