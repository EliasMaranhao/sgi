package br.com.easysoftware.sgiapi.exceptionhandler.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(content = Include.NON_NULL)
@Getter
@Builder
public class ExceptionInfo {
    
    private LocalDateTime dataHora;
    private int status;
    private String type;
    private String title;
    private String detail;
}
