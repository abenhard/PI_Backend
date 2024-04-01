package br.csi.PI_Backend.service.compra;

import br.csi.PI_Backend.infra.exceptions.CarrinhoNotFoundException;
import br.csi.PI_Backend.infra.exceptions.ProdutoNotFoundException;
import br.csi.PI_Backend.model.produto.*;
import br.csi.PI_Backend.model.usuario.Usuario;
import br.csi.PI_Backend.model.usuario.UsuarioRepository;
import br.csi.PI_Backend.model.compra.carrinho.Carrinho;
import br.csi.PI_Backend.model.compra.carrinho.CarrinhoDTO;
import br.csi.PI_Backend.model.compra.carrinho.CarrinhoRepository;
import br.csi.PI_Backend.model.compra.produto_carrinho.Produto_Carrinho;
import br.csi.PI_Backend.model.compra.produto_carrinho.Produto_CarrinhoDTO;
import br.csi.PI_Backend.model.compra.produto_carrinho.Produto_CarrinhoRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    private final CarrinhoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final Produto_CarrinhoRepository produtoCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository repository, Produto_CarrinhoRepository produtoCarrinhoRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.produtoCarrinhoRepository = produtoCarrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CarrinhoDTO findByUsuario(String login){

        Usuario usuario = this.usuarioRepository.findByLogin(login);

        Carrinho carrinho = this.repository.findCarrinhoByUsuarioCarrinho(usuario);

        return convertToDto(carrinho);

    }
    public Set<CarrinhoDTO> findAll() {
        List<Carrinho> carrinhos = this.repository.findAll();
        return carrinhos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet());
    }

    private CarrinhoDTO convertToDto(Carrinho carrinho) {
        Set<Produto_CarrinhoDTO> produtosDto = new HashSet<>();

        for (Produto_Carrinho produtoCarrinho: carrinho.getProdutosCarrinho())
        {
            Produto_CarrinhoDTO produto_carrinhoDTO= new Produto_CarrinhoDTO();

           if(produtoCarrinho.getProduto().getDisponibilidade() == Disponibilidade.indisponivel.toString())
           {
               carrinho.getProdutosCarrinho().remove(produtoCarrinho);
           }
            else {
               produto_carrinhoDTO.setIdCarrinho(produtoCarrinho.getCarrinho().getId());
               produto_carrinhoDTO.setIdProduto(produtoCarrinho.getProduto().getId());
               produto_carrinhoDTO.setNomeProduto(produtoCarrinho.getProduto().getNome());
               produto_carrinhoDTO.setPrecoUnitario(produtoCarrinho.getProduto().getPrecoBase());
               produto_carrinhoDTO.setQuantidade(produtoCarrinho.getQuantidade());
               produto_carrinhoDTO.setDisponibilidade(Disponibilidade.disponivel);

               produtosDto.add(produto_carrinhoDTO);
           }
        }

        return new CarrinhoDTO(carrinho.getUsuarioCarrinho().getNome(), carrinho.getId(), carrinho.getPrecoTotal(), produtosDto);
    }
    public void cadastrar(Carrinho carrinho) {
        this.repository.save(carrinho);
    }

    public CarrinhoDTO findCarrinho(Long id) {

       Carrinho carrinho = this.repository.getReferenceById(id);

        return convertToDto(carrinho);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void adicionarProdutoAoCarrinho(Produto_CarrinhoDTO produtoCarrinhoDTO, String login) {

        Carrinho carrinho = SetCarrinhoComLogin(login);

        Produto produto = SetProduto(produtoCarrinhoDTO, carrinho);

        Produto_Carrinho produtoCarrinho = this.produtoCarrinhoRepository
                .findProduto_CarrinhoByCarrinhoIdAndProdutoId(produtoCarrinhoDTO.getIdCarrinho(), produtoCarrinhoDTO.getIdProduto())
                .orElse(null);
        if (produtoCarrinho == null) {
            produtoCarrinho = new Produto_Carrinho();
            produtoCarrinho.setCarrinho(carrinho);
            produtoCarrinho.setProduto(produto);
            produtoCarrinho.setQuantidade(produtoCarrinhoDTO.getQuantidade());
        } else {
            produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + produtoCarrinhoDTO.getQuantidade());
        }

        this.produtoCarrinhoRepository.save(produtoCarrinho);


        BigDecimal precoTotal = carrinho.getProdutosCarrinho().stream()
                .map(pc -> pc.getProduto().getPrecoBase().multiply(BigDecimal.valueOf(pc.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        carrinho.setPrecoTotal(precoTotal);

        this.repository.save(carrinho);
    }
    @Transactional
    public void removerProdutoDoCarrinho(Produto_CarrinhoDTO produtoCarrinhoDTO, String login) {
        try {
            Carrinho carrinho = SetCarrinhoComLogin(login);
            Produto produto = SetProduto(produtoCarrinhoDTO, carrinho);

            Produto_Carrinho produtoCarrinho = produtoCarrinhoRepository
                    .findProduto_CarrinhoByCarrinhoIdAndProdutoId(produtoCarrinhoDTO.getIdCarrinho(), produtoCarrinhoDTO.getIdProduto())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto no carrinho não encontrado"));

            int quantidadeRemover = produtoCarrinhoDTO.getQuantidade();

            int novaQuantidade = Math.max(produtoCarrinho.getQuantidade() - quantidadeRemover, 0);


            if (novaQuantidade>0) {
                produtoCarrinho.setQuantidade(novaQuantidade);
                this.produtoCarrinhoRepository.save(produtoCarrinho);

            } else {
                carrinho.getProdutosCarrinho().remove(produtoCarrinho);
                produto.getProdutosCarrinho().remove(produtoCarrinho);
                this.repository.save(carrinho);
                this.produtoRepository.save(produto);
                this.produtoCarrinhoRepository.delete(produtoCarrinho);
            }

            carrinho.setPrecoTotal(calculaPricoTotal(carrinho));

        } catch (OptimisticLockException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal calculaPricoTotal(Carrinho carrinho) {
        return carrinho.getProdutosCarrinho().stream()
                .map(pc -> pc.getProduto().getPrecoBase().multiply(BigDecimal.valueOf(pc.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private Carrinho SetCarrinhoComLogin(String login)
    {
        Usuario usuario = this.usuarioRepository.findByLogin(login);

        Carrinho carrinho = this.repository.findCarrinhoByUsuarioCarrinho(usuario);
        if(carrinho ==null){ throw new CarrinhoNotFoundException("Carrinho não encontrado");}

        return carrinho;
    }
    private Produto SetProduto(Produto_CarrinhoDTO produtoCarrinhoDTO, Carrinho carrinho){

        produtoCarrinhoDTO.setIdCarrinho(carrinho.getId());
        return  this.produtoRepository.findById(produtoCarrinhoDTO.getIdProduto())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
    }
}

