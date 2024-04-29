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
    @Size(min = 10, max =11, message = "CPF inválido")
    @Column(name = "cpf")
    private String cpf;

    public Pessoa(String nome, String email, String telefone, String whatsApp, String cpf){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.whatsapp = whatsApp;
        this.cpf = cpf;
    }
}
