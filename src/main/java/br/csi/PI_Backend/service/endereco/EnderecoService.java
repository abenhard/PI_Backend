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

    private final CidadeService cidadeService;
    private final UFService ufService;

    public EnderecoService(EnderecoRepository repository, CidadeService cidadeService, UFService ufRepository) {
        this.repository = repository;
        this.cidadeService = cidadeService;
        this.ufService = ufRepository;
    }

    public boolean cadastrar(EnderecoDTO enderecoDTO, Pessoa pessoa) {
        if(pessoa == null)
        {
            System.out.println("Pessoa Não Cadastrada");
            return false;
        }

        Endereco enderecoSalvar = new Endereco();
        enderecoSalvar.setRua(enderecoDTO.getRua());
        enderecoSalvar.setBairro(enderecoDTO.getBairro());
        enderecoSalvar.setComplemento(enderecoDTO.getComplemento());
        enderecoSalvar.setCep(enderecoDTO.getCep());
        enderecoSalvar.setNumero(enderecoDTO.getNumero());


        Estado estado = ufService.getUfPorNome(enderecoDTO.getEstado());
        enderecoSalvar.setCidade(cidadeService.getOrCreateCidade(enderecoDTO.getCidade(), estado.getNome()));
        enderecoSalvar.setPessoa(pessoa);

        this.repository.save(enderecoSalvar);
        return true;
    }
    public Endereco findById(Long id) {
        return this.repository.findById(id).get();
    }

    public Endereco findByCep(String cep) {
        return this.repository.findByCep(cep).get();
    }

    public void atualizar(Endereco endereco){
       Endereco enderecoCadastrar = this.repository.getReferenceById(endereco.getId());
       if(enderecoCadastrar != null) {
           setEndereco(enderecoCadastrar);
       }
       else{
           System.out.println("Endereço não encontrado");
       }
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    void setEndereco(Endereco endereco){
        endereco.setRua(endereco.getRua());
        endereco.setBairro(endereco.getBairro());
        endereco.setCep(endereco.getCep());
        endereco.setComplemento(endereco.getComplemento());
        endereco.setCidade(endereco.getCidade());
    }

}
