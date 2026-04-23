package com.easysoftware.sgi_api.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Contato {
    
    private String instagram;
    private String telefone;
    private String whatsapp;
    private String email;
}
