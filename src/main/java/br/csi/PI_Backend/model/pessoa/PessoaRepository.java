package br.csi.PI_Backend.model.pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Pessoa getById(Long id);
    public Pessoa getPessoaByCpf(String cpf);

}
