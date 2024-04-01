package br.csi.PI_Backend.service.usuario;

import br.csi.PI_Backend.model.endereco.EnderecoRepository;
import br.csi.PI_Backend.model.usuario.DadosUsuario;
import br.csi.PI_Backend.model.usuario.Usuario;
import br.csi.PI_Backend.model.usuario.UsuarioRepository;
import br.csi.PI_Backend.model.compra.carrinho.Carrinho;
import br.csi.PI_Backend.model.compra.carrinho.CarrinhoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final CarrinhoRepository carrinhoRepository;
    private final EnderecoRepository enderecoRepository;
    public UsuarioService(UsuarioRepository repository, CarrinhoRepository carrinhoRepository, EnderecoRepository enderecoRepository){
        this.repository = repository;
        this.carrinhoRepository = carrinhoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Usuario cadastrar(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuarioCarrinho(usuario);
        carrinho.setPrecoTotal(BigDecimal.valueOf(0));
        this.repository.save(usuario);
        carrinhoRepository.save(carrinho);
        return usuario;
    }
    public void atualizar(Usuario usuario){
        Usuario usuarioAtualizar = this.repository.getReferenceById(usuario.getId());
        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setLogin(usuario.getLogin());
        usuarioAtualizar.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioAtualizar.setTelefone(usuario.getTelefone());
        usuarioAtualizar.setCpf(usuario.getCpf());
        usuarioAtualizar.setDataNascimento(usuario.getDataNascimento());
    }
    public DadosUsuario findUsuario(Long id)
    {
        Usuario usuario = this.repository.getReferenceById(id);
        return  new DadosUsuario(usuario);
    }
    public Usuario findByLogin(String login)
    {
        return this.repository.findByLogin(login);
    }
    public DadosUsuario findByLoginDTO(String login)
    {
        Usuario usuario = this.repository.findByLogin(login);

        return new DadosUsuario(usuario);
    }
    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }

    public void excluir(Long id){
        Usuario usuario = this.repository.getReferenceById(id);

        carrinhoRepository.deleteById(usuario.getCarrinho().getId());
        this.repository.delete(usuario);
    }
}
