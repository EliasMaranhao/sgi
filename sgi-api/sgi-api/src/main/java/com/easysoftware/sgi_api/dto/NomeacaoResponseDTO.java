package com.easysoftware.sgi_api.dto;

import java.time.LocalDate;

public record NomeacaoResponseDTO(
    String cargoNome,
    LocalDate dataPosse,
    LocalDate dataSaida,
    String departamentoNome
) {}
