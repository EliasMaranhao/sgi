package com.easysoftware.sgi_api.domain.exception;

public class MembroNaoEncontradoException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public MembroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public MembroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
