package br.csi.PI_Backend.model.ordem_servico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name ="Tipo_Servico")
@Table(name ="tipos_de_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tipo_Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @NotBlank
    @Column(name="custo_base")
    private BigDecimal custo_base;
}
