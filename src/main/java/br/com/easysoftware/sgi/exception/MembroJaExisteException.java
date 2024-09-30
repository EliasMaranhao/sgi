package br.com.easysoftware.sgi.exception;

public class MembroJaExisteException extends RuntimeException{
    private String mensagem;

    public MembroJaExisteException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
