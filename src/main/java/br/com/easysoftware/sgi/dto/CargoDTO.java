package br.com.easysoftware.sgi.dto;

import java.time.LocalDate;

public record CargoDTO(String funcao,
                       LocalDate dataPosse,
                       LocalDate dataDestituicao) {
    
}
