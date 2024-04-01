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

    public Funcionario findByLogin(String login){ return this.repository.findFuncionarioByLogin(login);}

    public Boolean Cadastrar(DadosFuncionario dadosFuncionario){
        Funcionario funcionario = new Funcionario();

        funcionario.setPessoa(pessoaService.findByCpf(dadosFuncionario.cpf()));
        funcionario.setLogin(dadosFuncionario.login());
        funcionario.setSenha(dadosFuncionario.senha());
        funcionario.setAtivo(true);
        funcionario.setCargo(cargoRepository.findByNome(dadosFuncionario.cargo()));
        funcionario.setData_entrada(dadosFuncionario.data_de_entrada());
        funcionario.setSalario(dadosFuncionario.salario());

        try {
            this.repository.save(funcionario);
            return true;
        }catch (Exception e){
            System.out.println("error:" + e);
            return false;
        }
    }
}
