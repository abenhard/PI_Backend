package br.csi.PI_Backend.controller;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioRepository;
import br.csi.PI_Backend.model.pessoa.Pessoa;
import br.csi.PI_Backend.model.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check")
public class CheckController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/funcionario/{cpf}")
    public ResponseEntity<?> checkFuncionarioByCpf(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        if (funcionario != null) {
            return ResponseEntity.ok("Funcionario already exists");
        } else {
            Pessoa pessoa = pessoaRepository.getPessoaByCpf(cpf);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        Pessoa pessoa = pessoaRepository.getByEmail(email);
        if (pessoa != null) {
            return ResponseEntity.ok("Email already registered");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
