package br.csi.PI_Backend.service.endereco;


import br.csi.PI_Backend.model.endereco.Estado;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.endereco.Endereco;
import br.csi.PI_Backend.model.endereco.EnderecoDTO;
import br.csi.PI_Backend.model.endereco.EnderecoRepository;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.stereotype.Service;


@Service
public class EnderecoService {
    private final EnderecoRepository repository;
    private final PessoaService pessoaService;
    private final CidadeService cidadeService;
    private final UFService ufService;

    public EnderecoService(EnderecoRepository repository, CidadeService cidadeService, UFService ufRepository, PessoaService pessoaService) {
        this.repository = repository;
        this.cidadeService = cidadeService;
        this.ufService = ufRepository;
        this.pessoaService = pessoaService;
    }

    public void cadastrar(EnderecoDTO enderecoDTO, String cpf) {
        Endereco enderecoSalvar = new Endereco();
        enderecoSalvar.setRua(enderecoDTO.getRua());
        enderecoSalvar.setBairro(enderecoDTO.getBairro());
        enderecoSalvar.setComplemento(enderecoDTO.getComplemento());
        enderecoSalvar.setCep(enderecoDTO.getCep());
        enderecoSalvar.setNumero(enderecoDTO.getNumero());


        Estado estado = ufService.getUfPorNome(enderecoDTO.getUf());
        enderecoSalvar.setCidade(cidadeService.getOrCreateCidade(enderecoDTO.getCidade(), estado.getNome()));

        Pessoa pessoa = pessoaService.findByCpf(cpf);
        enderecoSalvar.setPessoa(pessoa);

        this.repository.save(enderecoSalvar);

    }

    public EnderecoDTO listar(String cpf) {

        Endereco endereco = this.repository.findByPessoa(this.pessoaService.findByCpf(cpf));

        return convertToEnderecoDTO(endereco);
    }

    public Endereco findById(Long id) {
        return this.repository.findById(id).get();
    }

    public Endereco findByCep(String cep) {
        return this.repository.findByCep(cep).get();
    }

    public void atualizar(Endereco endereco){
       Endereco enderecoCadastrar = this.repository.getReferenceById(endereco.getId());
        enderecoCadastrar.setRua(endereco.getRua());
        enderecoCadastrar.setBairro(endereco.getBairro());
        enderecoCadastrar.setCep(endereco.getCep());
        enderecoCadastrar.setComplemento(endereco.getComplemento());
        enderecoCadastrar.setCidade(endereco.getCidade());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    public EnderecoDTO convertToEnderecoDTO(Endereco endereco)
    {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                endereco.getRua(), endereco.getBairro(), endereco.getComplemento(),
                endereco.getCep(), endereco.getNumero(), endereco.getCidade().getNome()
                , endereco.getCidade().getEstado().getNome());

        return enderecoDTO;
    }
}
