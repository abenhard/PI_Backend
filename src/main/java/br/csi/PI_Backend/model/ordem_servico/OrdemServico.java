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
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name ="OrdemServico")
@Table(name ="ordens_de_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotNull
    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull
    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "status_id")
    private Status status;

    @NotNull
    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tipo_de_servico_id")
    private Tipo_Servico tipo_servico;

    @NotNull
    @NotBlank
    @Column(name = "descricao_problema")
    private String descricao_problema;

    @Column(name = "produto_extra")
    private String produto_extra;

    @Column(name ="relatorio_tecnico")
    private String relatorio_tecnico;

    @Column(name = "custo_total")
    private BigDecimal custo_total;

    @NotNull
    @NotBlank
    @Column(name= "data_criacao")
    private Timestamp data_criacao;

    @Column(name="data_previsao")
    private Date data_previsao;

    @Column(name="data_entrega")
    private Date data_entrega;
}
