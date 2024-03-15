package br.csi.gg_store.controller.compra;


import br.csi.gg_store.infra.security.TokenServiceJWT;
import br.csi.gg_store.model.compra.Compra;
import br.csi.gg_store.model.compra.CompraDTO;
import br.csi.gg_store.model.compra.StatusCompra;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.compra.CarrinhoService;
import br.csi.gg_store.service.compra.CompraService;
import br.csi.gg_store.service.usuario.UsuarioService;
import br.csi.gg_store.service.usuario.endereco.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService service;
    private final CarrinhoService carrinhoService;
    private final EnderecoService enderecoService;
    private UsuarioService usuarioService;
    private TokenServiceJWT tokenService;


    public CompraController(CompraService service, CarrinhoService carrinhoService, EnderecoService enderecoService,UsuarioService usuarioService, TokenServiceJWT tokenService)
    {
        this.service = service;
        this.carrinhoService = carrinhoService;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }
    @GetMapping
    public ResponseEntity<Set<CompraDTO>> getCompra(HttpServletRequest request)
    {
        Usuario usuario = getUsuario(request);

        Set<CompraDTO> compras = this.service.getCompraByUsuario(usuario);

        return ResponseEntity.ok(compras);

    }
    @GetMapping("/todasCompras")
    public ResponseEntity<List<Compra>> getTodasCompras()
    {


       List<Compra> compras = this.service.findAllCompras();

        return ResponseEntity.ok(compras);

    }
    @PostMapping("/{idEndereco}")
    public ResponseEntity<String> comprar(HttpServletRequest request, @PathVariable Long idEndereco) {
        Usuario usuario = getUsuario(request);

        if(this.enderecoService.findByUsuarioAndId(usuario, idEndereco))
        {
            this.service.salvarCompra(usuario, idEndereco);

            return ResponseEntity.ok("Compra cadastrada com sucesso!");
        }
        else{
            return ResponseEntity.badRequest().body("Endereço Não encontrado!");
        }

    }
    @PutMapping("cancelarCompra/{idCompra}")
    public ResponseEntity<String> cancelarCompra(HttpServletRequest request, @PathVariable Long idCompra) {
        Usuario usuario = getUsuario(request);

        Compra compra = this.service.findCompraByUsuarioEId(usuario, idCompra);

        if(compra == null)
        {
            return ResponseEntity.badRequest().body("Compra não encontrada!");
        }
        else if(compra.getStatus() == StatusCompra.concluida)
        {
            return ResponseEntity.badRequest().body("Compra já foi concluida!");
        }
        else{
            this.service.mudarStatusCompra(compra, StatusCompra.cancelada);
            return ResponseEntity.ok("Compra Cancelada com sucesso!");
        }
    }
    @PutMapping("/concluirCompra/{idCompra}")
    public ResponseEntity<String> concluirCompra(HttpServletRequest request, @PathVariable Long idCompra) {

        Compra compra = this.service.getCompraById(idCompra);

        if(compra == null)
        {
            return ResponseEntity.badRequest().body("Compra não encontrada!");
        }
        else if(compra.getStatus() == StatusCompra.cancelada)
        {
            return ResponseEntity.badRequest().body("Compra já foi cancelada!");
        }
        else{
            this.service.mudarStatusCompra(compra, StatusCompra.concluida);
            return ResponseEntity.ok("Compra Concluida com sucesso!");
        }
    }

    private Usuario getUsuario(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String login = tokenService.getSubject(token);

        Usuario usuario = this.usuarioService.findByLogin(login);
        return usuario;
    }
}
