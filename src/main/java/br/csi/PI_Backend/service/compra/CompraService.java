package br.csi.PI_Backend.service.compra;

import br.csi.PI_Backend.model.compra.Compra;
import br.csi.PI_Backend.model.compra.CompraDTO;
import br.csi.PI_Backend.model.compra.CompraRepository;
import br.csi.PI_Backend.model.compra.StatusCompra;
import br.csi.PI_Backend.model.compra.carrinho.Carrinho;
import br.csi.PI_Backend.model.compra.carrinho.CarrinhoRepository;
import br.csi.PI_Backend.model.compra.produto_Compra.Produto_Compra;
import br.csi.PI_Backend.model.compra.produto_Compra.Produto_CompraDTO;
import br.csi.PI_Backend.model.compra.produto_Compra.Produto_CompraRepository;
import br.csi.PI_Backend.model.compra.produto_carrinho.Produto_Carrinho;
import br.csi.PI_Backend.model.usuario.Usuario;
import br.csi.PI_Backend.model.endereco.Endereco;
import br.csi.PI_Backend.service.endereco.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompraService {

    private final CompraRepository repository;
    private final CarrinhoRepository carrinhoRepository;
    private final Produto_CompraRepository produtoCompraRepository;
    private final EnderecoService enderecoService;

    public CompraService(CompraRepository repository, EnderecoService enderecoService, Produto_CompraRepository produtoCompraRepository, CarrinhoRepository carrinhoRepository)
    {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.produtoCompraRepository = produtoCompraRepository;
        this.carrinhoRepository = carrinhoRepository;
    }
    public Set<CompraDTO> getCompraByUsuario(Usuario usuario)
    {
       Set<Compra> compras = this.repository.findCompraByUsuarioVenda(usuario);
       Set<CompraDTO> compraDTOS = new HashSet<>();

       for(Compra compra: compras)
       {
           CompraDTO compraDTO = new CompraDTO();

           compraDTO.setId(compra.getId());
           compraDTO.setStatusCompra(compra.getStatus());
           compraDTO.setEnderecoDTO(this.enderecoService.convertToEnderecoDTO(compra.getEnderecoCompra()));
           compraDTO.setNomeUsuario(compra.getUsuarioVenda().getNome());
           compraDTO.setProdutoCompraDTOSet(convertToProduto_CompraDTO(compra.getProdutosCompra()));

           compraDTOS.add(compraDTO);
       }
       return compraDTOS;
    }
    public Compra getCompraById(Long id)
    {
        return this.repository.getCompraById(id);
    }
    public Compra findCompraByUsuarioEId(Usuario usuario, Long id)
    {
        return this.repository.findCompraByUsuarioVendaAndId(usuario,id);
    }
    public List<Compra> findAllCompras()
    {
        return this.repository.findAll();
    }
    public void salvarCompra(Usuario usuario, Long idEndereco){
        Endereco endereco = this.enderecoService.findById(idEndereco);
        Carrinho carrinho = this.carrinhoRepository.findCarrinhoByUsuarioCarrinho(usuario);
        Set<Produto_Compra> produtoCompraSet = new HashSet<>();

        Compra compra = new Compra();

        compra.setEnderecoCompra(endereco);
        compra.setStatus(StatusCompra.aberta);
        compra.setUsuarioVenda(usuario);
        compra.setPrecoTotal(carrinho.getPrecoTotal());
        this.repository.save(compra);

        for(Produto_Carrinho produtoCarrinho: carrinho.getProdutosCarrinho())
        {
            Produto_Compra produtoCompra= new Produto_Compra();
            produtoCompra.setCompra(compra);
            produtoCompra.setProduto(produtoCarrinho.getProduto());
            produtoCompra.setQuantidade(produtoCarrinho.getQuantidade());

            produtoCompraSet.add(produtoCompra);
            this.produtoCompraRepository.save(produtoCompra);
        }

        Set<Produto_Carrinho> zeraCarrinho = new HashSet<>();
        carrinho.setProdutosCarrinho(zeraCarrinho);

        this.carrinhoRepository.save(carrinho);

        compra.setProdutosCompra(produtoCompraSet);
    }
    public void mudarStatusCompra(Compra compra, StatusCompra statusCompra)
    {
        compra.setStatus(statusCompra);
        this.repository.save(compra);
    }
    public Set<Produto_CompraDTO> convertToProduto_CompraDTO(Set<Produto_Compra> produtoCompras)
    {
        Set<Produto_CompraDTO> produtoCompraDTOS = new HashSet<>();

        for(Produto_Compra produtoCompra: produtoCompras)
        {
            Produto_CompraDTO produtoCompraDTO = new Produto_CompraDTO();

            produtoCompraDTO.setProduto(produtoCompra.getProduto().getNome());
            produtoCompraDTO.setQuantidade(produtoCompra.getQuantidade());

            produtoCompraDTOS.add(produtoCompraDTO);
        }
        return produtoCompraDTOS;
    }
}
