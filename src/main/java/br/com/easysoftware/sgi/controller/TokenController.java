package br.com.easysoftware.sgi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.LoginRequestDTO;
import br.com.easysoftware.sgi.dto.LoginResponseDTO;
import br.com.easysoftware.sgi.service.UsuarioService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class TokenController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
        LoginResponseDTO loginResponse = usuarioService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }
}
