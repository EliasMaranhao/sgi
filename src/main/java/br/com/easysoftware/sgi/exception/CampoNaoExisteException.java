package br.com.easysoftware.sgi.exception;

public class CampoNaoExisteException extends RuntimeException{
    private String mensagem;

    public CampoNaoExisteException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
