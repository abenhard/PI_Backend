package br.csi.PI_Backend.service.autenticacao;

import br.csi.PI_Backend.model.funcionario.Funcionario;
import br.csi.PI_Backend.model.funcionario.FuncionarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final FuncionarioRepository repository;

    public AutenticacaoService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Funcionario funcionario = this.repository.findByLogin(login);
        if (funcionario == null) {
            throw new UsernameNotFoundException("Funcionário não encontrado");
        } else {
            UserDetails user = User.withUsername(funcionario.getLogin())
                    .password(funcionario.getSenha())
                    .authorities(funcionario.getCargo().getNome())
                    .build();
            return user;
        }
    }
}
