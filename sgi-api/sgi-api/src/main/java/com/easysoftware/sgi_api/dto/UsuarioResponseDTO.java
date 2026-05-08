package com.easysoftware.sgi_api.dto;

import com.easysoftware.sgi_api.entities.RoleUsuario;

public record UsuarioResponseDTO(
    Long id,
    String login,
    RoleUsuario role,
    Long filialId,
    String nomeFilial
) {}
