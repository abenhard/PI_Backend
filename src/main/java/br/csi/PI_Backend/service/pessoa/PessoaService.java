package br.csi.PI_Backend.service.pessoa;

import br.csi.PI_Backend.model.pessoa.*;
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
    public Boolean Atualizar(Pessoa pessoa){
        Pessoa pessoaAtualizar = this.repository.getPessoaByCpf(pessoa.getCpf());
        System.out.println("Pessoa enviada= " + pessoa.getNome());
        System.out.println("Pessoa Atualizar= " + pessoaAtualizar.getNome());
        if(pessoaAtualizar==null)return false;

        try {
            pessoaAtualizar.setNome(pessoa.getNome());
            pessoaAtualizar.setTelefone(pessoa.getTelefone());
            pessoaAtualizar.setWhatsapp(pessoa.getWhatsapp());
            pessoaAtualizar.setCpf(pessoa.getCpf());
            pessoaAtualizar.setRua(pessoa.getRua());
            pessoaAtualizar.setBairro(pessoa.getBairro());
            pessoaAtualizar.setComplemento(pessoa.getComplemento());
            pessoaAtualizar.setCep(pessoa.getCep());
            pessoaAtualizar.setNumero(pessoa.getNumero());
            pessoaAtualizar.setCidade(pessoa.getCidade());
            pessoaAtualizar.setEstado(pessoa.getEstado());

            this.repository.save(pessoaAtualizar);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public Boolean Atualizar(PessoaEnderecoDTO pessoaEnderecoDTO){
        PessoaDTO pessoaDTO = pessoaEnderecoDTO.getPessoaDTO();
        EnderecoDTO enderecoDTO = pessoaEnderecoDTO.getEnderecoDTO();

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

            this.repository.save(pessoaAtualizar);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean jaExiste(PessoaDTO pessoaDTO){
        return this.repository.getPessoaByCpfOrEmailOrNome(pessoaDTO.nome(), pessoaDTO.cpf(), pessoaDTO.email()).isEmpty();
    }
    public Pessoa getByCpf(String cpf){
        return this.repository.getPessoaByCpf(cpf);
    }
    public Pessoa getByNome(String nome){ return  this.repository.getPessoaByNome(nome);}
    public List<Pessoa> findByCpfOrNome(String cpfOrNome){
        return this.repository.findPessoaByCpfOrNome(cpfOrNome);
    }
    public Pessoa getByEmail(String email){return this.repository.getByEmail(email);}
    public Pessoa getById(Long id){ return this.repository.getById(id);}
    public List<Pessoa> getAllPessoa(){

        return this.repository.findAll();
    }
    public List<Pessoa> getClientesOnly(){
        return this.repository.findPessoasNotInFuncionarios();
    }
}
