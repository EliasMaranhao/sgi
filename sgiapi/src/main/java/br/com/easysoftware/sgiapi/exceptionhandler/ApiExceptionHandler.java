package br.com.easysoftware.sgiapi.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.ExceptionInfo;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MembroNaoEncontradoException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(MembroNaoEncontradoException.class)
    public ResponseEntity<?> tratarMembroNaoEncontradoException(MembroNaoEncontradoException e){
        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .mensagem(e.getMessage())
                                            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(info);
    }
}
