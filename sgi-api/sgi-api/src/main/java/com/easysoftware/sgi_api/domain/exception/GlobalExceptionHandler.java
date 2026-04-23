package com.easysoftware.sgi_api.domain.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroResposta> handleBusinessException(BusinessException ex, WebRequest request) {
        ErroResposta erro = new ErroResposta(HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            LocalDateTime.now(),
            request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Tratando erros de validação (ex: @NotBlank, @NotNull nas Entidades)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ErroResposta erro = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação: " + mensagem,
            LocalDateTime.now(),
            "Campos inválidos"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Tratando qualquer outro erro inesperado (Erro 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGenerico(Exception ex) {
        ErroResposta erro = new ErroResposta(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocorreu um erro interno inesperado no servidor.",
            LocalDateTime.now(),
            ex.getMessage()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
