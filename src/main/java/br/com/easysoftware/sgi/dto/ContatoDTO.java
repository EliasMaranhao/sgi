package br.com.easysoftware.sgi.dto;

import br.com.easysoftware.sgi.entity.TipoContato;

public record ContatoDTO(Long id,
                         TipoContato tipoContato,
                         String valor) {
    
}
