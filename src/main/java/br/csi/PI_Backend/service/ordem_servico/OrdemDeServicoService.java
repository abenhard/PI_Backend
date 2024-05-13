package br.csi.PI_Backend.service.ordem_servico;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServico;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoDTO;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoRepository;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemDeServicoService {
    private final OrdemDeServicoRepository repository;
    private final PessoaService pessoaService;
    private final FuncionarioService funcionarioService;

    public OrdemDeServicoService(OrdemDeServicoRepository repository, PessoaService pessoaService, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
        this.funcionarioService = funcionarioService;
    }

    public List<OrdemDeServicoDTO> findOrdemDeServicosByPessoaEquals(Pessoa pessoa){
        List<OrdemDeServico> ordemDeServicos = repository.findOrdemDeServicosByPessoaEquals(pessoa);
        if(ordemDeServicos == null){
            return null;
        }

        return setDTOS(ordemDeServicos);
    }
    public List<OrdemDeServicoDTO> findOrdemDeServicosByFuncionarioEquals(String login){
        Funcionario funcionario = funcionarioService.findByLogin(login);
        List<OrdemDeServico> ordemDeServicos = repository.findOrdemDeServicosByFuncionarioEquals(funcionario);
        return setDTOS(ordemDeServicos);
    }
    public List<OrdemDeServicoDTO> findOrdemDeServicosByPessoaEqualsAndFuncionarioEquals(Pessoa pessoa, Funcionario funcionario){
        List<OrdemDeServico> ordemDeServicos = repository.findOrdemDeServicosByPessoaEqualsAndFuncionarioEquals(pessoa, funcionario);
        return setDTOS(ordemDeServicos);
    }
    private List<OrdemDeServicoDTO> setDTOS(List<OrdemDeServico> ordemDeServicos){
        List<OrdemDeServicoDTO> ordemDeServicoDTOS = null;

        for (OrdemDeServico ordemDeServico: ordemDeServicos) {
            OrdemDeServicoDTO ordemDeServicoDTO= new OrdemDeServicoDTO(
                    pessoaService.getById(ordemDeServico.getPessoa().getId()).getNome(),
                    funcionarioService.getById(ordemDeServico.getFuncionario().getId()).getPessoa().getNome(),
                    ordemDeServico.getStatus(),
                    ordemDeServico.getTipo_servico(),
                    ordemDeServico.getDescricao_problema(),
                    ordemDeServico.getProduto_extra(),
                    ordemDeServico.getRelatorio_tecnico(),
                    ordemDeServico.getCusto_total(),
                    ordemDeServico.getData_criacao(),
                    ordemDeServico.getData_previsao(),
                    ordemDeServico.getData_entrega(),
                    ordemDeServico.getImagem_caminho(),
                    ordemDeServico.getLocalizacao()
            );

            ordemDeServicoDTOS.add(ordemDeServicoDTO);
        }

        return ordemDeServicoDTOS;
    }
}
