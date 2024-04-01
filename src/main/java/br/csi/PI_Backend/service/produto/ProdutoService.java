package br.csi.PI_Backend.service.produto;

import br.csi.PI_Backend.model.produto.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final MarcaService marcaService;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repository, MarcaService marcaService, CategoriaService categoriaService){
        this.repository = repository;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    public void cadastrar(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPrecoBase(produtoDTO.getPrecoBase());
        produto.setDisponibilidade(produto.getDisponibilidade());
        Marca marca = marcaService.getMarcaPorNome(produtoDTO.getMarca());

        produto.setMarca(marca);

        Set<Categoria> categorias = new HashSet<>();

        for (String categoria : produtoDTO.getCategorias()) {
            categorias.add(categoriaService.getCategoriaPorNome(categoria));
        }

        for (Categoria categoria : categorias) {
            Set<Produto> produtos = categoria.getProdutos();
            produtos.add(produto);
        }
        produto.setDisponibilidade(Disponibilidade.disponivel.toString());
        produto.setCategorias(categorias);
        System.out.println("Disponibilidade" + produto.getDisponibilidade());
        this.repository.save(produto);
    }


    public List<Produto> listar(){
        return this.repository.findAll();
    }

    public Produto findById(Long id){
        return this.repository.findById(id).get();
    }
    public List<Produto> getProdutoPorNome(String nome)
    {
        return this.repository.getProdutoByNome(nome);
    }
    public void atualizar(ProdutoDTO produtoDTO){

        Produto produtoSalvar = this.repository.getReferenceById(produtoDTO.getId());
        produtoSalvar.setNome(produtoDTO.getNome());
        produtoSalvar.setDescricao(produtoDTO.getDescricao());
        produtoSalvar.setPrecoBase(produtoDTO.getPrecoBase());
        produtoSalvar.setDisponibilidade(produtoDTO.getDisponibilidade());

        Set<String> categoriasNome = produtoDTO.getCategorias();
        Set<Categoria> categorias = new HashSet<>();

        for(String categoria: categoriasNome)
        {
            categorias.add(this.categoriaService.getCategoriaPorNome(categoria));
        }

        produtoSalvar.setCategorias(categorias);
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
