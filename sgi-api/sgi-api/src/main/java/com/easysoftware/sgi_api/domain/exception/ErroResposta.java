package com.easysoftware.sgi_api.domain.exception;

import java.time.LocalDateTime;

public record ErroResposta(int status,           // Código HTTP (ex: 400, 404, 500)
                           String mensagem,      // Mensagem amigável para o usuário
                           LocalDateTime data,   // Momento em que o erro ocorreu
                           String detalhe)        // Caminho da requisição ou stacktrace curto (opcional))
    
{}
