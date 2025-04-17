package br.com.easysoftware.sgiapi.entities;

import lombok.Getter;

@Getter
public enum StatusEnum {
    MATRIZ("Matriz"),
    CONGREGACAO("Congregacao");

    public String status;

    StatusEnum(String status){
        this.status = status;
    }
}
