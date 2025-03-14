package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

public class MembroNaoEncontradoException extends EntidadeNaoEncontradaException{

    public MembroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public MembroNaoEncontradoException(Long id){
        this(String.format("Membro de identificador %s não foi encontrada", id));
    }
}
