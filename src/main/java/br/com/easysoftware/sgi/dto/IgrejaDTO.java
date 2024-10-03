package br.com.easysoftware.sgi.dto;

import br.com.easysoftware.sgi.entity.Denominacao;
import br.com.easysoftware.sgi.entity.Endereco;

public record IgrejaDTO(Long id,
                        String nome,
                        Endereco endereco,
                        Long campo,
                        Denominacao denominacao
                        ) {
    
}
