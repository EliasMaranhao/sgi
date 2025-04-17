package br.com.easysoftware.sgiapi.entities;

import lombok.Getter;

@Getter
public enum ParentescoEnum {
    PAI("Pai"),
    MAE("Mae"),
    IRMAO("Irmao"),
    IRMA("Irma"),
    PRIMO("Primo"),
    PRIMA("Prima"),
    AVO("Avo"),
    PADRASTO("Padrastro"),
    MADRASTA("Madrasta"),
    FILHO("Filho"),
    FILHA("Filha"),
    OUTROS("Outros");

    private String grau;

    ParentescoEnum(String grau){
        this.grau = grau;
    }
}
