package br.csi.PI_Backend.model.pessoa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name ="Pessoa")
@Table(name ="pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "telefone")
    private String telefone;


    @Email(message = "Email inválido")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "whatsapp")
    private String whatsapp;

    @NotBlank
    @Size(min = 10, max = 11, message = "CPF inválido")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "complemento")
    private String complemento;

    @NotBlank
    @Column(name = "rua")
    private String rua;

    @NotBlank
    @Column(name = "bairro")
    private String bairro;

    @NotBlank
    @Size(min = 8, max = 9, message = "CEP inválido")
    @Column(name = "cep")
    private String cep;

    @NotBlank
    @Column(name = "numero", nullable = false)
    private String numero;

    @NotBlank
    @Column(name = "cidade")
    private String cidade;

    @NotBlank
    @Column(name = "estado")
    private String estado;

    public Pessoa(String nome, String telefone, String email, String whatsapp, String cpf, String complemento, String rua, String bairro, String cep, String numero, String cidade, String estado) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.whatsapp = whatsapp;
        this.cpf = cpf;
        this.complemento = complemento;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }
}
