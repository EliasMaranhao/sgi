package com.easysoftware.sgi_api.dto;

import jakarta.validation.constraints.NotBlank;

public record MatrizDTO(
    @NotBlank
    String nome,

    @NotBlank
    String pastorPresidente,

    @NotBlank
    String vicePresidente,

    @NotBlank
    String denominacao
) {}
