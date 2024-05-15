package br.csi.PI_Backend.service.funcionario;

import br.csi.PI_Backend.model.funcionario.*;
import br.csi.PI_Backend.model.pessoa.PessoaDTO;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.service.pessoa.PessoaService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Funcionario getById(Long id){
        return repository.getById(id);
    }

    public Funcionario findByLogin(String login){ return this.repository.findByLogin(login);}

    public List<Funcionario> findAllFuncionarios(){

        return this.repository.findAll();

    }
    public boolean Cadastrar(FuncionarioCadastro funcionarioCadastro){
        if(findByLogin(funcionarioCadastro.funcionarioDTO().login())!= null){
            return false;
        }

        Pessoa pessoa = pessoaService.getByCpf(funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().cpf());
        if(pessoa == null){
            PessoaDTO pessoaDTO = new PessoaDTO(
                    funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().nome(),
                    funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().email(),
                    funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().telefone(),
                    funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().whatsapp(),
                    funcionarioCadastro.pessoaEnderecoDTO().getPessoaDTO().cpf());

            pessoa = pessoaService.Cadastrar(pessoaDTO, funcionarioCadastro.pessoaEnderecoDTO().getEnderecoDTO());
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
    public void Alterar(FuncionarioDTO funcionarioDTO)
    {
        Funcionario funcionario = findByLogin(funcionarioDTO.login());

        funcionario.setLogin(funcionarioDTO.login());
        funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionarioDTO.senha()));
        funcionario.setAtivo(funcionarioDTO.ativo());
        funcionario.setCargo(cargoService.findByNome(funcionarioDTO.cargo()));

        try {
            this.repository.save(funcionario);
        }catch (Exception e){
            System.out.println("error: " + e);
        }
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
