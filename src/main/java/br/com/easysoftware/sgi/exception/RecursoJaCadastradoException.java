package br.com.easysoftware.sgi.exception;

public class RecursoJaCadastradoException extends RuntimeException{
    
    private String mensagem;

    public RecursoJaCadastradoException(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }
}
