package br.csi.PI_Backend.model.endereco;

import br.csi.PI_Backend.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco getById(Long id);
    public Optional<Endereco> findById(Long id);
    public Optional<Endereco> findByCep(String cep);

    public Endereco findByPessoa(Pessoa pessoa);
}
