package br.csi.PI_Backend.model.funcionario;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario getById(Long id);
    public Funcionario findByLogin(String login);

    public Funcionario findFuncionarioByPessoa(Pessoa pessoa);

    @Query("SELECT f FROM Funcionario f WHERE f.pessoa.cpf = :cpf")
    Funcionario findFuncionarioByCpf(@Param("cpf") String cpf);

    public List<Funcionario> findFuncionariosByCargoIs(Cargo cargo);
    public List<Funcionario> findAll();
}
