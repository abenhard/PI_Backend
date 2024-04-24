package br.csi.PI_Backend.model.funcionario;

import br.csi.PI_Backend.model.pessoa.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
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
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotBlank
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @NotBlank
    @Column(name = "login")
    private String login;

    @NotBlank
    @Column(name = "senha")
    private String senha;

    @NotBlank
    @Column(name = "ativo")
    private Boolean ativo;

    public String getCargoString(){
        return cargo.toString();
    }
}
