package br.csi.PI_Backend.service.funcionario;

import br.csi.PI_Backend.model.funcionario.*;
import br.csi.PI_Backend.model.pessoa.DadosPessoa;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.stereotype.Service;

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

    public Funcionario findByLogin(String login){ return this.repository.findByLogin(login);}

    public List<Funcionario> findAllFuncionarios(){

        return this.repository.findAll();

    }
    public Boolean Cadastrar(FuncionarioCadastro funcionario){
        Pessoa pessoa = pessoaService.getById(funcionario.pessoa_id());
        Cargo cargo = cargoService.getById(funcionario.cargo_id());
        System.out.println(pessoa.getNome());
        Funcionario funcionarioCadastrar = new Funcionario(
                pessoa,
               cargo,
                funcionario.login(),
                funcionario.senha(),
                funcionario.ativo()
        );



        try {
            this.repository.save(funcionarioCadastrar);
            return true;
        }catch (Exception e){
            System.out.println("error: " + e);
            return false;
        }
    }
    public Boolean Cadastrar(DadosFuncionario dadosFuncionario){
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = pessoaService.findByCpf(dadosFuncionario.cpf());
        if(pessoa == null){
            DadosPessoa dadosPessoa = new DadosPessoa(
                    dadosFuncionario.nome(),
                    dadosFuncionario.email(),
                    dadosFuncionario.telefone(),
                    dadosFuncionario.whatsapp(),
                    dadosFuncionario.cpf());

            pessoa = pessoaService.Cadastrar(dadosPessoa);
        }
        funcionario.setPessoa(pessoa);
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoService.findByNome(dadosFuncionario.cargo()));

        try {
            this.repository.save(funcionario);
            return true;
        }catch (Exception e){
            System.out.println("error: " + e);
            return false;
        }
    }
    public void Alterar(DadosFuncionario dadosFuncionario)
    {
        Funcionario funcionario = findByLogin(dadosFuncionario.cpf());

        //funcionario.setLogin(dadosFuncionario.login());
        //funcionario.setSenha(dadosFuncionario.senha());
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoService.findByNome(dadosFuncionario.cargo()));

        try {
            this.repository.save(funcionario);
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }
//    public void Excluir(DadosFuncionario dadosFuncionario)
//    {
//        //Funcionario funcionario = findByLogin(dadosFuncionario.login());
//
//        funcionario.setAtivo(false);
//
//        try {
//            this.repository.save(funcionario);
//        }catch (Exception e){
//            System.out.println("error: " + e);
//        }
//    }
}
