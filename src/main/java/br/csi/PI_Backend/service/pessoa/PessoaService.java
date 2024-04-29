package br.csi.PI_Backend.service.pessoa;

import br.csi.PI_Backend.model.endereco.EnderecoDTO;
import br.csi.PI_Backend.model.pessoa.PessoaDTO;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.pessoa.PessoaRepository;
import br.csi.PI_Backend.service.endereco.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository repository;
    private final EnderecoService enderecoService;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.repository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public Pessoa Cadastrar(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO){
        Pessoa pessoaCadastrar = new Pessoa(pessoaDTO.nome(), pessoaDTO.email(),
                pessoaDTO.telefone(), pessoaDTO.whatsapp(), pessoaDTO.cpf());

        try {
            this.repository.save(pessoaCadastrar);
            this.enderecoService.cadastrar(enderecoDTO, pessoaCadastrar);

        }catch (Exception e){
            System.out.println("error:" + e);
        }
        finally {
            return pessoaCadastrar;
        }
    }
    public Boolean Atualizar(PessoaDTO pessoaDTO){
        Pessoa pessoaAtualizar = this.repository.getPessoaByCpf(pessoaDTO.cpf());
        if(pessoaAtualizar==null)return false;

        pessoaAtualizar.setNome(pessoaDTO.nome());
        pessoaAtualizar.setTelefone(pessoaDTO.telefone());
        pessoaAtualizar.setWhatsapp(pessoaDTO.whatsapp());
        pessoaAtualizar.setCpf(pessoaDTO.cpf());

        return true;
    }

    public Pessoa getByCpf(String id){
        return this.repository.getPessoaByCpf(id);
    }
    public Pessoa getByEmail(String email){return this.repository.getByEmail(email);}
    public Pessoa getById(Long id){ return this.repository.getById(id);}
    public List<Pessoa> getAllPessoa(){
        return this.repository.findAll();
    }
}
