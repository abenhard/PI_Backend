package br.csi.PI_Backend.infra.exceptions;

public class RecursoNotFoundException extends RuntimeException{
    public RecursoNotFoundException(String mensagem){
        super(mensagem);
    }
}
