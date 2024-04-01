package br.csi.PI_Backend.infra.exceptions;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}