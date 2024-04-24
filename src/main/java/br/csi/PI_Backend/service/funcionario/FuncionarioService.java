package br.csi.PI_Backend.service.funcionario;

import br.csi.PI_Backend.model.funcionario.CargoRepository;
import br.csi.PI_Backend.model.funcionario.DadosFuncionario;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioRepository;
import br.csi.PI_Backend.model.pessoa.DadosPessoa;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final CargoRepository cargoRepository;
    private final PessoaService pessoaService;

    public FuncionarioService(FuncionarioRepository repository, PessoaService pessoaService, CargoRepository cargoRepository) {
        this.repository = repository;
        this.pessoaService =pessoaService;
        this.cargoRepository= cargoRepository;
    }

    public Funcionario findByLogin(String login){ return this.repository.findByLogin(login);}

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

        funcionario.setLogin(dadosFuncionario.login());
        funcionario.setSenha(dadosFuncionario.senha());
        funcionario.setPessoa(pessoa);
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoRepository.findByNome(dadosFuncionario.cargo()));

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

        funcionario.setLogin(dadosFuncionario.login());
        funcionario.setSenha(dadosFuncionario.senha());
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoRepository.findByNome(dadosFuncionario.cargo()));

        try {
            this.repository.save(funcionario);
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }
    public void Excluir(DadosFuncionario dadosFuncionario)
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
