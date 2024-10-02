package br.com.easysoftware.sgi.exception;

public class RecursoNaoExisteException extends RuntimeException{
    private String mensagem;

    public RecursoNaoExisteException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
