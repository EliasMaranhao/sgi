package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

public class MinisterioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public MinisterioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public MinisterioNaoEncontradoException(Long id){
        this(String.format("Ministerio de identificador %s não foi encontrada", id));
    }
}
