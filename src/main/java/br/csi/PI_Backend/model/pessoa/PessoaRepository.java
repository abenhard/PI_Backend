package br.csi.PI_Backend.model.pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
   Pessoa getById(Long id);
   @Query("SELECT p FROM Pessoa p WHERE p.cpf LIKE %:query% OR p.nome LIKE %:query%")
   List<Pessoa> findPessoaByCpfOrNome(@Param("query") String query);
   Pessoa getPessoaByCpf(String cpf);
   Pessoa getByEmail(String email);

   @Query("SELECT p FROM Pessoa p WHERE p.nome = :nome OR p.cpf = :cpf OR p.email = :email")
   List<Pessoa> getPessoaByCpfOrEmailOrNome(@Param("nome") String nome, @Param("cpf") String cpf, @Param("email") String email);
}
