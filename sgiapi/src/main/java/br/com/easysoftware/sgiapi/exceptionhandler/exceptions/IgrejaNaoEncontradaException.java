package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

public class IgrejaNaoEncontradaException extends EntidadeNaoEncontradaException{

    public IgrejaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    
    public IgrejaNaoEncontradaException(Long id){
        this(String.format("Igreja de identificador %s não foi encontrada", id));
    }
}
