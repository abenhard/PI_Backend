package br.csi.PI_Backend.model.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    public Categoria(Long id){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "idcategoria"),
            inverseJoinColumns = @JoinColumn(name = "idproduto")
    )
    private Set<Produto> produtos = new HashSet<>();
}
