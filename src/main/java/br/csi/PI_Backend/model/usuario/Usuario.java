package br.csi.PI_Backend.model.usuario;

import br.csi.PI_Backend.model.compra.carrinho.Carrinho;
import br.csi.PI_Backend.model.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name ="usuario")
@Table(name ="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Email(message = "Email inválido")
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "senha")
    private String senha;

    @Column(name = "permissao")
    private String permissao;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "telefone")
    private String telefone;

    @NotBlank
    @Size(min = 10, max =11, message = "CPF inválido")
    @Column(name = "cpf")
    private String cpf;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToOne(mappedBy = "usuarioCarrinho")
    @JsonIgnore
    private Carrinho carrinho;
}
