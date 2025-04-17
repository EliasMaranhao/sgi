package br.com.easysoftware.sgiapi.entities;

public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String sexo;

    SexoEnum(String sexo){
        this.sexo = sexo;
    }

    public String getSexo(){
        return this.sexo;
    }
}
