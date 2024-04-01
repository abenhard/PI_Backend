package br.csi.PI_Backend.model.funcionario;

import br.csi.PI_Backend.model.pessoa.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name ="Funcionario")
@Table(name ="funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @Column(name = "pessoa_id")
    private Pessoa pessoa;

    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @Column(name = "cargo_id")
    private Cargo cargo;

    @NotBlank
    @NotNull
    @Column(name = "login")
    private String login;

    @NotBlank
    @NotNull
    @Column(name = "senha")
    private String senha;

    @NotBlank
    @Column(name = "ativo")
    private Boolean ativo;

    @NotBlank
    @Column(name = "data_entrada")
    private Date data_entrada;

    @Column(name = "data_saida")
    private Date data_saida;

    @NotBlank
    @NotNull
    @Column(name = "salario")
    private BigDecimal salario;

}
