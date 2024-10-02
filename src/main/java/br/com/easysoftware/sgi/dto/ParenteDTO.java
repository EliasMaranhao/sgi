package br.com.easysoftware.sgi.dto;

import br.com.easysoftware.sgi.entity.TipoParentesco;

public record ParenteDTO(TipoParentesco parentesco,
                         String nome) {
    
}
