package br.csi.PI_Backend.model.ordem_servico;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {

    List<OrdemDeServico> findOrdemDeServicosByPessoaEqualsAndFuncionarioEquals(Pessoa pessoa, Funcionario funcionario);
    List<OrdemDeServico> findOrdemDeServicosByFuncionarioEquals(Funcionario funcionario);
    List<OrdemDeServico> findOrdemDeServicosByPessoaEquals(Pessoa pessoa);
}
