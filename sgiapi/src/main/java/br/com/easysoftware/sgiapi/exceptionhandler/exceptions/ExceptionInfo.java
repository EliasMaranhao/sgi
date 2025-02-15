package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionInfo {
    
    private LocalDateTime dataHora;
    private String mensagem;
}
