package br.csi.PI_Backend.infra.exceptions;

public class CarrinhoNotFoundException extends RuntimeException {
    public CarrinhoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
