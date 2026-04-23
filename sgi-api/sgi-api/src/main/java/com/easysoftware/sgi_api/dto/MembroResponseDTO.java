package com.easysoftware.sgi_api.dto;

import java.time.LocalDate;
import java.util.List;

import com.easysoftware.sgi_api.entities.StatusMembro;

public record MembroResponseDTO(
    String nome,
    LocalDate dataBatismo,
    StatusMembro statusMembro,
    String fotoUrl,
    String filial,
    List<String> nomeacoes
) {}
