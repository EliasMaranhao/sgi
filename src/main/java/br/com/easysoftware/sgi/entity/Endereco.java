package br.com.easysoftware.sgi.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Endereco {

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String pais;
}
