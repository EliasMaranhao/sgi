package br.com.easysoftware.sgiapi.model;

import lombok.Data;

@Data
public class Endereco {
    
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
