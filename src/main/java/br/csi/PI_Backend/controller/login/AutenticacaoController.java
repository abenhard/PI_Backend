package br.csi.PI_Backend.controller.login;

import br.csi.PI_Backend.infra.security.TokenServiceJWT;
import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.service.funcionario.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private final AuthenticationManager manager;
    private final FuncionarioService funcionarioService;
    private final TokenServiceJWT tokenServiceJWT;
    public AutenticacaoController(AuthenticationManager manager, FuncionarioService funcionarioService, TokenServiceJWT tokenServiceJWT){
        this.manager = manager;
        this.funcionarioService = funcionarioService;
        this.tokenServiceJWT = tokenServiceJWT;
    }
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        try{
            Funcionario funcionario = funcionarioService.findByLogin(dados.login());

            if(funcionario.getAtivo())
            {
                Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
                Authentication at = manager.authenticate(autenticado);

                User user = (User) at.getPrincipal();
                String token = this.tokenServiceJWT.gerarToken(user);

                return ResponseEntity.ok().body(new DadosTokenJWT(token));
            }
            else {
                return ResponseEntity.badRequest().body("Funcionario Não Encontrado");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Falha no Login");
        }
    }
    private record DadosTokenJWT(String token){}
    private record DadosAutenticacao(String login, String senha){ }
}
