package br.csi.PI_Backend.service.pessoa;

import br.csi.PI_Backend.model.funcionario.DadosFuncionario;
import br.csi.PI_Backend.model.pessoa.DadosPessoa;
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

    public Pessoa Cadastrar(DadosPessoa dadosPessoa){
        Pessoa pessoaCadastrar = cadastro(dadosPessoa.nome(), dadosPessoa.telefone(), dadosPessoa.whatsapp(), dadosPessoa.cpf());

        try {
            this.repository.save(pessoaCadastrar);

        }catch (Exception e){
            System.out.println("error:" + e);
        }
        finally {
            return pessoaCadastrar;
        }
    }
    public Pessoa cadastro(String nome, String telefone, String whatsApp, String cpf){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setWhatsapp(whatsApp);
        pessoa.setCpf(cpf);

        return pessoa;
    }
    public Boolean Atualizar(DadosPessoa dadosPessoa){
        Pessoa pessoaAtualizar = this.repository.getPessoaByCpf(dadosPessoa.cpf());
        if(pessoaAtualizar==null)return false;

        pessoaAtualizar.setNome(dadosPessoa.nome());
        pessoaAtualizar.setTelefone(dadosPessoa.telefone());
        pessoaAtualizar.setWhatsapp(dadosPessoa.whatsapp());
        pessoaAtualizar.setCpf(dadosPessoa.cpf());

        return true;
    }

    public Pessoa findByCpf(String id){
        return this.repository.getPessoaByCpf(id);
    }
    public Pessoa getById(Long id){ return this.repository.getById(id);}
    public List<Pessoa> getAllPessoa(){
        return this.repository.findAll();
    }
}
