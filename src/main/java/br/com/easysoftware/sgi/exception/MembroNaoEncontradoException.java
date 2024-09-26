package br.com.easysoftware.sgi.exception;

public class MembroNaoEncontradoException extends RuntimeException{
    
    private String mensagem;

    public MembroNaoEncontradoException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
