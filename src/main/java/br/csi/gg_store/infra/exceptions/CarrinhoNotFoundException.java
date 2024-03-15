package br.csi.gg_store.infra.exceptions;

public class CarrinhoNotFoundException extends RuntimeException {
    public CarrinhoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
