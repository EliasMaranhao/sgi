package br.com.easysoftware.sgi.dto;

import java.time.LocalDate;

import br.com.easysoftware.sgi.entity.Funcao;

public record CargoDTO(Funcao funcao,
                       LocalDate dataPosse,
                       LocalDate dataDestituicao) {
    
}
