package br.csi.PI_Backend.service.pessoa;

import br.csi.PI_Backend.model.pessoa.EnderecoDTO;
import br.csi.PI_Backend.model.pessoa.PessoaDTO;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.pessoa.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository repository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.repository = pessoaRepository;
    }

    public Pessoa Cadastrar(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO){

        Pessoa pessoaCadastrar = new Pessoa(
                pessoaDTO.nome(), pessoaDTO.telefone(),
                pessoaDTO.email(),pessoaDTO.whatsapp(),
                pessoaDTO.cpf(),enderecoDTO.getComplemento(),
                enderecoDTO.getRua(), enderecoDTO.getBairro(),
                enderecoDTO.getCep(), enderecoDTO.getNumero(),
                enderecoDTO.getCidade(),enderecoDTO.getEstado()
                );

        try {
            this.repository.save(pessoaCadastrar);
        }catch (Exception e){
            System.out.println("error:" + e);
        }
        finally {
            return pessoaCadastrar;
        }
    }
    public Boolean Atualizar(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO){
        Pessoa pessoaAtualizar = this.repository.getPessoaByCpf(pessoaDTO.cpf());
        if(pessoaAtualizar==null)return false;

        try {
            pessoaAtualizar.setNome(pessoaDTO.nome());
            pessoaAtualizar.setTelefone(pessoaDTO.telefone());
            pessoaAtualizar.setWhatsapp(pessoaDTO.whatsapp());
            pessoaAtualizar.setCpf(pessoaDTO.cpf());
            pessoaAtualizar.setRua(enderecoDTO.getRua());
            pessoaAtualizar.setBairro(enderecoDTO.getBairro());
            pessoaAtualizar.setComplemento(enderecoDTO.getComplemento());
            pessoaAtualizar.setCep(enderecoDTO.getCep());
            pessoaAtualizar.setNumero(enderecoDTO.getNumero());
            pessoaAtualizar.setCidade(enderecoDTO.getCidade());
            pessoaAtualizar.setEstado(enderecoDTO.getEstado());

            return true;
        }
        catch (Exception e){
            return false;
        }
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
