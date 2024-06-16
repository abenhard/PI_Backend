package br.csi.PI_Backend.service.ordem_servico;

import br.csi.PI_Backend.BackEndUrl;
import br.csi.PI_Backend.infra.exceptions.RecursoNotFoundException;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.ordem_servico.*;
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
import java.util.ArrayList;
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

    public boolean cadastrarOrdemAtendente(OrdemDeServicoAtendenteDTO ordemDeServicoDTO) {
        String orderFolderName = UUID.randomUUID().toString();
        String caminhoImagens = "upload/imagens/" + orderFolderName + "/";

        OrdemDeServico ordemDeServico = new OrdemDeServico(
                pessoaService.getByCpf(ordemDeServicoDTO.clienteCPF()),
                funcionarioService.findByLogin(ordemDeServicoDTO.funcionariologin()),
                ordemDeServicoDTO.status(),
                ordemDeServicoDTO.tipo_servico(),
                ordemDeServicoDTO.descricao_problema()
        );
        ordemDeServico.setData_criacao(Timestamp.from(Instant.now()));
        ordemDeServico.setImagem_caminho(caminhoImagens);

        try {
            this.repository.save(ordemDeServico);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void cadastrarOrdemTecnico(OrdemDeServicoDTO ordemDeServicoDTO, MultipartFile[] fotos) {
        String orderFolderName = UUID.randomUUID().toString();
        String caminhoImagens = "upload/imagens/" + orderFolderName + "/";

        OrdemDeServico ordemDeServico = new OrdemDeServico(
                pessoaService.getByCpf(ordemDeServicoDTO.getClienteCPF()),
                funcionarioService.findByLogin(ordemDeServicoDTO.getFuncionarioLogin()),
                "ORCAMENTO FINALIZADO",
                ordemDeServicoDTO.getTipo_servico(),
                ordemDeServicoDTO.getDescricao_problema(),
                ordemDeServicoDTO.getProduto_extra(),
                ordemDeServicoDTO.getRelatorio_tecnico(),
                null,
                Timestamp.from(Instant.now()),
                ordemDeServicoDTO.getData_previsao(),
                caminhoImagens,
                ordemDeServicoDTO.getLocalizacao()
        );
        if(fotos != null && fotos.length >0){
            salvaFotos(fotos, ordemDeServico);
        }
        this.repository.save(ordemDeServico);
    }


    public void alterar(OrdemDeServicoDTO ordemDeServicoDTO, MultipartFile[] fotos) {
        OrdemDeServico ordemDeServico = repository.findById(ordemDeServicoDTO.getId())
                .orElseThrow(() -> new RecursoNotFoundException("Ordem de Servico não encontrada id :: " + ordemDeServicoDTO.getId()));

        ordemDeServico.setStatus(ordemDeServicoDTO.getStatus());
        ordemDeServico.setTipo_servico(ordemDeServicoDTO.getTipo_servico());
        ordemDeServico.setDescricao_problema(ordemDeServicoDTO.getDescricao_problema());
        ordemDeServico.setProduto_extra(ordemDeServicoDTO.getProduto_extra());
        ordemDeServico.setRelatorio_tecnico(ordemDeServicoDTO.getRelatorio_tecnico());
        ordemDeServico.setCusto_total(ordemDeServicoDTO.getCusto_total());
        ordemDeServico.setData_previsao(ordemDeServicoDTO.getData_previsao());
        ordemDeServico.setData_entrega(ordemDeServicoDTO.getData_entrega());
        ordemDeServico.setLocalizacao(ordemDeServicoDTO.getLocalizacao());

        if(fotos != null && fotos.length >0){
            salvaFotos(fotos, ordemDeServico);
        }
        this.repository.save(ordemDeServico);
    }
    public List<OrdemDeServicoExibicaoDTO> getOrdensDeServico(){
        List<OrdemDeServico> ordemDeServicos = repository.findAll();
        return setOrdemDeServicoExibicaoDTO(ordemDeServicos);
    }
    public OrdemDeServico findOrdemDeServicosById(Long id) {
        return repository.getById(id);
    }

    public List<OrdemDeServicoExibicaoDTO> getOrdensDeServicoByFuncionarioEquals(String login) {

        Funcionario funcionario = funcionarioService.findByLogin(login);

        List<OrdemDeServico> ordemDeServicos = repository.getOrdemDeServicosByFuncionarioEquals(funcionario);

        List<OrdemDeServicoExibicaoDTO> ordemDeServicoDTOS = setOrdemDeServicoExibicaoDTO(ordemDeServicos);

        return ordemDeServicoDTOS;
    }

    public List<OrdemDeServicoDTO> findOrdemDeServicosByPessoaEqualsAndFuncionarioEquals(Pessoa pessoa, Funcionario funcionario) {
        List<OrdemDeServico> ordemDeServicos = repository.findOrdemDeServicosByPessoaEqualsAndFuncionarioEquals(pessoa, funcionario);
        return setOrdemDeServicoDTO(ordemDeServicos);
    }

    private List<OrdemDeServicoDTO> setOrdemDeServicoDTO(List<OrdemDeServico> ordemDeServicos) {
        List<OrdemDeServicoDTO> ordemDeServicoDTOS = new ArrayList<>();;

        for (OrdemDeServico ordemDeServico : ordemDeServicos) {
            OrdemDeServicoDTO ordemDeServicoDTO = new OrdemDeServicoDTO(
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
    private List<OrdemDeServicoExibicaoDTO> setOrdemDeServicoExibicaoDTO(List<OrdemDeServico> ordemDeServicos) {
        List<OrdemDeServicoExibicaoDTO> ordemDeServicoExibicaoDTOS = new ArrayList<>();

        for (OrdemDeServico ordemDeServico : ordemDeServicos) {
            List<String> imageUrls = getImageUrls(ordemDeServico.getImagem_caminho());

            OrdemDeServicoExibicaoDTO ordemDeServicoDTO = new OrdemDeServicoExibicaoDTO(
                    ordemDeServico.getId(),
                    pessoaService.getById(ordemDeServico.getPessoa().getId()).getCpf(),
                    funcionarioService.getById(ordemDeServico.getFuncionario().getId()).getLogin(),
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
                    ordemDeServico.getLocalizacao(),
                    pessoaService.getById(ordemDeServico.getPessoa().getId()).getNome(),
                    funcionarioService.getById(ordemDeServico.getFuncionario().getId()).getPessoa().getNome(),
                    imageUrls
            );
            ordemDeServicoExibicaoDTOS.add(ordemDeServicoDTO);
        }

        return ordemDeServicoExibicaoDTOS;
    }

    private static void salvaFotos(MultipartFile[] fotos, OrdemDeServico ordemDeServico) {
        File folder = new File(ordemDeServico.getImagem_caminho());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (MultipartFile foto : fotos) {
            try {
                String filename = foto.getOriginalFilename();
                if (filename != null) {
                    File imageFile = new File(folder.getAbsolutePath() + "/" + filename);
                    Files.write(imageFile.toPath(), foto.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getImageUrls(String caminhoImage) {
        File folder = new File(caminhoImage);
        List<String> imageUrls = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String imageUrl = BackEndUrl.getInstance().getBackendUrl() + "upload/imagens/" + folder.getName() + "/" + file.getName();
                        imageUrls.add(imageUrl);
                    }
                }
            }
        }
        return imageUrls;
    }

}
