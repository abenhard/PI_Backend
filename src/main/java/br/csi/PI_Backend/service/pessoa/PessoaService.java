package br.csi.PI_Backend.service.pessoa;

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

    public Boolean Cadastrar(DadosPessoa dadosPessoa){
        Pessoa pessoaCadastrar = new Pessoa();

        pessoaCadastrar.setNome(dadosPessoa.nome());
        pessoaCadastrar.setTelefone(dadosPessoa.telefone());
        pessoaCadastrar.setWhatsapp(dadosPessoa.whatsapp());
        pessoaCadastrar.setCpf(dadosPessoa.cpf());
        pessoaCadastrar.setData_Nascimento(dadosPessoa.dataNascinemento());

        try {
            this.repository.save(pessoaCadastrar);
            return true;
        }catch (Exception e){
            System.out.println("error:" + e);
            return false;
        }
    }
    public Boolean Atualizar(DadosPessoa dadosPessoa){
        Pessoa pessoaAtualizar = this.repository.getPessoaByCpf(dadosPessoa.cpf());
        if(pessoaAtualizar==null)return false;

        pessoaAtualizar.setNome(dadosPessoa.nome());
        pessoaAtualizar.setTelefone(dadosPessoa.telefone());
        pessoaAtualizar.setWhatsapp(dadosPessoa.whatsapp());
        pessoaAtualizar.setCpf(dadosPessoa.cpf());
        pessoaAtualizar.setData_Nascimento(dadosPessoa.dataNascinemento());

        return true;
    }

    public Pessoa findByCpf(String id){
        return this.repository.getPessoaByCpf(id);
    }
    public List<Pessoa> getAllPessoa(){
        return this.repository.findAll();
    }
}
