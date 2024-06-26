package br.csi.PI_Backend.service.funcionario;

import br.csi.PI_Backend.model.funcionario.*;
import br.csi.PI_Backend.model.pessoa.PessoaDTO;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final CargoService cargoService;
    private final PessoaService pessoaService;

    public FuncionarioService(FuncionarioRepository repository, PessoaService pessoaService, CargoService cargoService) {
        this.repository = repository;
        this.pessoaService =pessoaService;
        this.cargoService = cargoService;
    }
    public Funcionario getById(Long id){
        return repository.getById(id);
    }

    public Funcionario findByLogin(String login){ return this.repository.findByLogin(login);}

    public List<Funcionario> findTecnicos(){
        Cargo cargo = this.cargoService.findByNome("TECNICO");
        return this.repository.findFuncionariosByCargoIs(cargo);
    }
    public List<PessoaFuncionario> findAllFuncionarios(){

        List<Funcionario> funcionarios = this.repository.findAll();
        List<PessoaFuncionario> pessoaFuncionarioList = new ArrayList<>();

        for(Funcionario funcionario: funcionarios){
            Pessoa pessoa = funcionario.getPessoa();

            if(pessoa!=null){
                PessoaFuncionario pessoaFuncionario = new PessoaFuncionario(pessoa, funcionario);
                pessoaFuncionarioList.add(pessoaFuncionario);
            }
        }


        return pessoaFuncionarioList;

    }
    public boolean Cadastrar(FuncionarioCadastro funcionarioCadastro){
        if(findByLogin(funcionarioCadastro.funcionarioDTO().login())!= null){
            return false;
        }

        Pessoa pessoa = pessoaService.getByCpf(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().cpf());
        if(pessoa == null){
            pessoa = pessoaService.Cadastrar(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO(), funcionarioCadastro.pessoaEnderecoDTO().getEnderecoDTO());
        }
        Funcionario funcionario = new Funcionario();

        funcionario.setPessoa(pessoa);
        funcionario.setLogin(pessoa.getEmail());
        funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionarioCadastro.funcionarioDTO().senha()));
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoService.findByNome(funcionarioCadastro.funcionarioDTO().cargo()));

        try {
            this.repository.save(funcionario);
            return true;

        }catch (Exception e){
            System.out.println("error: " + e);
            return false;
        }
    }
    public void Alterar(FuncionarioCadastro funcionarioCadastro)
    {
        Funcionario funcionario = findByLogin(funcionarioCadastro.funcionarioDTO().login());

        pessoaService.Atualizar(funcionarioCadastro.pessoaEnderecoDTO());

        funcionario.setLogin(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().email());
        funcionario.setAtivo(funcionarioCadastro.funcionarioDTO().ativo());
        funcionario.setCargo(cargoService.findByNome(funcionarioCadastro.funcionarioDTO().cargo()));

        repository.save(funcionario);
    }
    public void Excluir(FuncionarioDTO dadosFuncionario)
    {
        Funcionario funcionario = findByLogin(dadosFuncionario.login());

        funcionario.setAtivo(false);

        try {
            this.repository.save(funcionario);
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }
}
