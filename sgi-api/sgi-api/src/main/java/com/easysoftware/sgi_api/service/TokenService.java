package com.easysoftware.sgi_api.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService{
    
    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "API Gestao Igreja";

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            
            // Tratamento preventivo para lazy loading de relacionamentos na criação do token
            Long igrejaId = (usuario.getFilial() != null) ? usuario.getFilial().getId() : null;

            return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getLogin())
                .withClaim("id", usuario.getId())
                .withClaim("igrejaId", igrejaId)
                .withClaim("role", usuario.getRole().name())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer(ISSUER)
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado.");
        }
    }

    private Instant dataExpiracao() {
        // Gera expiração robusta de 2 horas independente do fuso horário da máquina hospedada
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }
}
