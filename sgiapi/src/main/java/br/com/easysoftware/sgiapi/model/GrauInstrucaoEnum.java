package br.com.easysoftware.sgiapi.model;

public enum GrauInstrucaoEnum {

    SEM_INSTRUCAO("Sem instrucao"),
    ENSINO_FUNDAMENTAL_INCOMPLETO("Ensino fundamental incompleto"),
    ENSINO_FUNDAMENTAL_COMPLETO("Ensino fundamental completo"),
    ENSINO_MEDIO_COMPLETO("Ensino medio completo"),
    ENSINO_MEDIO_INCOMPLETO("Ensino medio completo"),
    ENSINO_SUPERIOR_COMPLETO("Ensino superior completo"),
    ENSINO_SUPERIOR_INCOMPLETO("Ensino superior incompleto"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private String grauInstrucao;

    GrauInstrucaoEnum(String grauInstrucao){
        this.grauInstrucao = grauInstrucao;
    }

    public String getGrauInstrucao(){
        return this.grauInstrucao;
    }
}
