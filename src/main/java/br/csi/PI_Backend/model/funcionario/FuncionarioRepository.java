package br.csi.PI_Backend.model.funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario getById(Long id);
    public Funcionario findByLogin(String login);
}
