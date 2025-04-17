package br.com.easysoftware.sgiapi.entities;

import lombok.Getter;

@Getter
public enum TipoContatoEnum {
    EMAIL("Email"),
    TELEFONE("Telefone");

    private String tipo;

    TipoContatoEnum(String tipo){
        this.tipo = tipo;
    }
}
