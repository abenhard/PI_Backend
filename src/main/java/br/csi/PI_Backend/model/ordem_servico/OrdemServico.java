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
    @Column(name = "pessoa_id")
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotNull
    @NotBlank
    @Column(name = "funcionario_id")
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull
    @NotBlank
    @Column(name = "status_id")
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "status_id")
    private Status status;

    @NotNull
    @NotBlank
    @Column(name = "tipo_de_servico_id")
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tipo_de_servico_id")
    private Tipo_Servico tipo_servico;

    @NotNull
    @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "placa_de_video")
    private String placa_de_video;

    @Column(name = "placa_mae")
    private String placa_mae;

    @Column(name = "memoria_ram")
    private String memoria_ram;

    @Column(name = "produto_extra")
    private String produto_extra;

    @Column(name ="detalhes_do_servico")
    private String detalhes_do_servico;

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
