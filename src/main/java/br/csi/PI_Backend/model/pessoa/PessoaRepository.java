package br.csi.PI_Backend.model.pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
   Pessoa getById(Long id);
   Pessoa getPessoaByCpf(String cpf);
   Pessoa getByEmail(String email);
}
