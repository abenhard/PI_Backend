package br.csi.gg_store.service.usuario.endereco;


import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.usuario.endereco.Endereco;
import br.csi.gg_store.model.usuario.endereco.EnderecoDTO;
import br.csi.gg_store.model.usuario.endereco.EnderecoRepository;
import br.csi.gg_store.model.usuario.endereco.UF;
import br.csi.gg_store.service.usuario.UsuarioService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;
    private final CidadeService cidadeService;
    private final UFService ufService;

    public EnderecoService(EnderecoRepository repository, CidadeService cidadeService, UFService ufRepository, UsuarioService usuarioService) {
        this.repository = repository;
        this.cidadeService = cidadeService;
        this.ufService = ufRepository;
        this.usuarioService = usuarioService;
    }

    public void cadastrar(EnderecoDTO enderecoDTO, String login) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setNumero(enderecoDTO.getNumero());


        UF uf = ufService.getUfPorNome(enderecoDTO.getUf());
        endereco.setCidade(cidadeService.getOrCreateCidade(enderecoDTO.getCidade(), uf));

        Usuario usuario = usuarioService.findByLogin(login);
        endereco.setUsuario(usuario);

        this.repository.save(endereco);

    }

    public List<EnderecoDTO> listar(String login) {
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        List<Endereco> enderecos = this.repository.findByUsuario(this.usuarioService.findByLogin(login));

        for (Endereco endereco : enderecos) {

            EnderecoDTO enderecoDTO = convertToEnderecoDTO(endereco);

            enderecoDTO.setLogin(login);
            enderecoDTO.setId(endereco.getId().toString());

            enderecoDTOS.add(enderecoDTO);
        }

        return enderecoDTOS;
    }

    public Endereco findById(Long id) {
        return this.repository.findById(id).get();
    }

    public Endereco findByCep(String cep) {
        return this.repository.findByCep(cep).get();
    }

    public boolean findByUsuarioAndId(Usuario usuario, Long id) {
        if (this.repository.findByUsuarioAndId(usuario, id) != null){
            return true;
        }
        else {
            return false;
        }
    }
    public void atualizar(Endereco endereco){
       Endereco enderecoSalvar = this.repository.getReferenceById(endereco.getId());
        enderecoSalvar.setRua(endereco.getRua());
        enderecoSalvar.setBairro(endereco.getBairro());
        enderecoSalvar.setCep(endereco.getCep());
        enderecoSalvar.setComplemento(endereco.getComplemento());
        enderecoSalvar.setCidade(endereco.getCidade());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    public EnderecoDTO convertToEnderecoDTO(Endereco endereco)
    {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                endereco.getRua(), endereco.getBairro(), endereco.getComplemento(),
                endereco.getCep(), endereco.getNumero(), endereco.getCidade().getNome()
                , endereco.getCidade().getUf().getNome());

        return enderecoDTO;
    }
}
