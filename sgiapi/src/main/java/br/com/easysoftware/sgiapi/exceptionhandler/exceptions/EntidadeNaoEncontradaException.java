package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

public class EntidadeNaoEncontradaException extends NegocioException{

    public EntidadeNaoEncontradaException(String mensagem, Throwable exception) {
        super(mensagem, exception);
    }
    
}
