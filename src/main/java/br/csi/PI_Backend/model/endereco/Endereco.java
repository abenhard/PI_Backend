package br.csi.PI_Backend.model.endereco;


import br.csi.PI_Backend.model.pessoa.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name ="enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complemento")
    private String complemento;

    @NotBlank
    @Column(name = "rua")
    private String rua;

    @NotBlank
    @Column(name = "bairro")
    private String bairro;

    @NotBlank
    @Size(min = 8, max =9, message = "CEP inválido")
    @Column(name = "cep")
    private String cep;

    @NotBlank
    @Column(name = "numero", nullable = false)
    private String numero;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
