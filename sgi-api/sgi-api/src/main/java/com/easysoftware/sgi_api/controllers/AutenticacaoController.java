package com.easysoftware.sgi_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easysoftware.sgi_api.dto.DadosAutenticacao;
import com.easysoftware.sgi_api.dto.DadosTokenJWT;
import com.easysoftware.sgi_api.entities.Usuario;
import com.easysoftware.sgi_api.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
public class AutenticacaoController {
    
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService){
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        System.out.println("CHEGOU AQUI................................");
        // 1. Cria o objeto de autenticação do Spring
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        
        // 2. O Manager chama o seu Service de autenticação e valida a senha
        var authentication = manager.authenticate(authenticationToken);

        // 3. Se passou na validação, gera o token para o usuário logado
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // 4. Devolve o token dentro do DTO para o Angular
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
