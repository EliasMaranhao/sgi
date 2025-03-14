package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

public class NegocioException extends RuntimeException{
    
    public NegocioException(String mensagem){
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable exception){
        super(mensagem, exception);
    }
}
