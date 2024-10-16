package br.com.easysoftware.sgi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.entity.Usuario;
import br.com.easysoftware.sgi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/sgi/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Usuario usuario){
        usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){
        List<Usuario> todos = usuarioService.listarTodos();
        return ResponseEntity.ok(todos);
    }
}
