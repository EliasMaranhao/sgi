package br.com.easysoftware.sgi.entity;

public enum Denominacao {
    ASSEMBLEIA_DE_DEUS("Assembléia de Deus"),
    BATISTA("Batista"),
    NOVA_VIDA("Nova Vida"),
    VIDA_NOVA("Vida Nova"),
    IGREJA_UNIVERSAL_DO_REINO_DE_DEUS("Igreja Universal do Reino de Deus"),
    DEUS_E_AMOR("Deus é Amor"),
    IGREJA_DA_RESTAURACAO("Igreja da Restauração");

    private String denominacao;

    Denominacao(String denominacao){
        this.denominacao = denominacao;
    }

    public String getDenominacao(){
        return this.denominacao;
    }
}
