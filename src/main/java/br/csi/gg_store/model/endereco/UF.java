package br.csi.gg_store.model.usuario.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name ="ufs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max=2, message= "Unidade Federal inválida, usar 2 digitos!")
    private String nome;

    @OneToMany(mappedBy = "uf")
    @JsonIgnore
    private List<Cidade> cidades;
}
