package com.easysoftware.sgi_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
    String id,
    @NotBlank String login,
    @NotBlank String senha,
    String role,
    @NotNull Long filialId
) {}
