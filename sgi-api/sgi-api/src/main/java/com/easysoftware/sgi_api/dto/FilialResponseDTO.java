package com.easysoftware.sgi_api.dto;

import java.time.LocalDate;

import com.easysoftware.sgi_api.entities.Contato;
import com.easysoftware.sgi_api.entities.Endereco;

public record FilialResponseDTO(
    Long id,
    String nome,
    String pastorDirigente,
    LocalDate dataInauguracao,
    Endereco endereco,
    Contato contato,
    String cnpj,
    MatrizResponseDTO matriz
) {}
