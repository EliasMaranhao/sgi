package com.easysoftware.sgi_api.dto;

public record FilialDTO(
    String nome,
    String pastorDirigente,
    String dataInauguracao,
    Long matriz_id,
    String cnpj
) {
    
}
