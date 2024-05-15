package br.csi.PI_Backend.service.ordem_servico;

import br.csi.PI_Backend.infra.exceptions.RecursoNotFoundException;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServico;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoDTO;
import br.csi.PI_Backend.model.ordem_servico.OrdemDeServicoRepository;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.Instant;

import java.util.List;
import java.util.UUID;

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
    public boolean cadastrarOrdemTecnico(OrdemDeServicoDTO ordemDeServicoDTO, MultipartFile[] fotos){
        if(!this.repository.findById(ordemDeServicoDTO.getId()).isPresent())
        {
            return false;
        }

        String orderFolderName = UUID.randomUUID().toString();
        String caminhoImagens = "src/main/resources/imagens" + orderFolderName + "/";

        OrdemDeServico ordemDeServico = new OrdemDeServico(
                pessoaService.getByCpf(ordemDeServicoDTO.getClienteCPF()),
                        funcionarioService.findByLogin(ordemDeServicoDTO.getFuncionario()),
                        ordemDeServicoDTO.getStatus(),
                        ordemDeServicoDTO.getTipo_servico(),
                        ordemDeServicoDTO.getDescricao_problema(),
                        ordemDeServicoDTO.getProduto_extra(),
                        ordemDeServicoDTO.getRelatorio_tecnico(),
                        ordemDeServicoDTO.getCusto_total(),
                        Timestamp.from(Instant.now()),
                        ordemDeServicoDTO.getData_previsao(),
                        ordemDeServicoDTO.getData_entrega(),
                        caminhoImagens,
                        ordemDeServicoDTO.getLocalizacao()
        );
        salvaFotos(fotos, ordemDeServico);
        this.repository.save(ordemDeServico);

        return true;
    }
    public void alterar(OrdemDeServicoDTO ordemDeServicoDTO, MultipartFile[] photos){
        OrdemDeServico ordemDeServico = repository.findById(ordemDeServicoDTO.getId()).orElseThrow(() -> new RecursoNotFoundException("Ordem de Servico não encontrada id :: " + ordemDeServicoDTO.getId()));

        ordemDeServico.setStatus(ordemDeServicoDTO.getStatus());
        ordemDeServico.setTipo_servico(ordemDeServicoDTO.getTipo_servico());
        ordemDeServico.setDescricao_problema(ordemDeServicoDTO.getDescricao_problema());
        ordemDeServico.setProduto_extra(ordemDeServicoDTO.getProduto_extra());
        ordemDeServico.setRelatorio_tecnico(ordemDeServicoDTO.getRelatorio_tecnico());
        ordemDeServico.setCusto_total(ordemDeServicoDTO.getCusto_total());
        ordemDeServico.setData_previsao(ordemDeServicoDTO.getData_previsao());
        ordemDeServico.setData_entrega(ordemDeServicoDTO.getData_entrega());
        ordemDeServico.setLocalizacao(ordemDeServicoDTO.getLocalizacao());
        salvaFotos(photos, ordemDeServico);

        this.repository.save(ordemDeServico);

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
                    ordemDeServico.getId(),
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
    private static void salvaFotos(MultipartFile[] photos, OrdemDeServico ordemDeServico) {
        File folder = new File(ordemDeServico.getImagem_caminho());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (MultipartFile photo : photos) {
            try {
                String filename = photo.getOriginalFilename();
                File imageFile = new File(folder.getAbsolutePath() + "/" + filename);
                Files.write(imageFile.toPath(), photo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception according to your requirements
            }
        }
    }
}
