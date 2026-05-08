package com.easysoftware.sgi_api.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.easysoftware.sgi_api.dto.UsuarioDTO;
import com.easysoftware.sgi_api.dto.UsuarioResponseDTO;
import com.easysoftware.sgi_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        UsuarioResponseDTO novoUsuario = usuarioService.cadastrar(dto);
        
        // Boa prática: Retornar 201 Created com a URI do novo recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoUsuario.id())
                    .toUri();
                    
        return ResponseEntity.created(uri).body(novoUsuario);
    }
}
