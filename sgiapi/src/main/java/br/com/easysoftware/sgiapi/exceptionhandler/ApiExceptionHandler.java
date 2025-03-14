package br.com.easysoftware.sgiapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeEmUsoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.ExceptionInfo;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MembroNaoEncontradoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MinisterioNaoEncontradoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.NegocioException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(MembroNaoEncontradoException.class)
    public ResponseEntity<?> tratarMembroNaoEncontradoException(MembroNaoEncontradoException e, WebRequest webRequest){
        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(e.getMessage())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .title("Membro não encontrado")
                                            .type("http://sgi.com.br/membro_nao_encontrado")
                                            .build();

        return handleExceptionInternal(e, info, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest){
        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(e.getMessage())
                                            .status(HttpStatus.CONFLICT.value())
                                            .title("Entidade em uso")
                                            .type("http://sgi.com.br/entidade_em_uso")
                                            .build();
        
        return handleExceptionInternal(e, info, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(MinisterioNaoEncontradoException.class)
    public ResponseEntity<?> handleMinisterioNaoEncontradoException(MinisterioNaoEncontradoException e, WebRequest webRequest){
        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(e.getMessage())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .title("Ministerio não encontrado")
                                            .type("http://sgi.com.br/ministerio_nao_encontrado")
                                            .build();

        return handleExceptionInternal(e, info, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest webRequest){
        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(e.getMessage())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .title("Erro de negócio")
                                            .type("http://sgi.com.br/erro_de_negocio")
                                            .build();

        return handleExceptionInternal(e, info, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, webRequest);
        }else if(rootCause instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, webRequest);
        }

        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(ex.getMessage())
                                            .status(HttpStatus.BAD_REQUEST.value())
                                            .title("Corpo da requisição é inválido")
                                            .type("http://sgi.com.br/erro_de_negocio")
                                            .build();
                                            
        return handleExceptionInternal(ex, info, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
		String path = joinPath(ex.getPath());
		
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
        path, ex.getValue(), ex.getTargetType().getSimpleName());

        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(detail)
                                            .status(status.value())
                                            .title("Corpo inválido")
                                            .type("http://sgi.com.br/mensagem_nao_compreensível")
                                            .build();
		
		return handleExceptionInternal(ex, info, headers, status, request);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, 
                                                            @Nullable Object body, 
                                                            HttpHeaders headers, 
                                                            HttpStatusCode httpStatusCode, 
                                                            WebRequest webRequest) {

        if(body == null){
            body = ExceptionInfo.builder().dataHora(LocalDateTime.now())
                .detail(exception.getMessage())
                .build();
        }else if(body instanceof String){
            body = ExceptionInfo.builder().dataHora(LocalDateTime.now())
                .detail((String) body)
                .build(); 
        }
       
        
        return super.handleExceptionInternal(exception, body, headers, httpStatusCode, webRequest);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());
        
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        ExceptionInfo info = ExceptionInfo.builder()
                                            .dataHora(LocalDateTime.now())
                                            .detail(detail)
                                            .status(status.value())
                                            .title("Corpo da requisição é inválido")
                                            .type("http://sgi.com.br/erro_de_negocio")
                                            .build();
        
        return handleExceptionInternal(ex, info, headers, status, request);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
            .map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));
    } 
    
    // 1. MethodArgumentTypeMismatchException é um subtipo de TypeMismatchException

    // 2. ResponseEntityExceptionHandler já trata TypeMismatchException de forma mais abrangente

    // 3. Então, especializamos o método handleTypeMismatch e verificamos se a exception
    //    é uma instância de MethodArgumentTypeMismatchException

    // 4. Se for, chamamos um método especialista em tratar esse tipo de exception

    // 5. Poderíamos fazer tudo dentro de handleTypeMismatch, mas preferi separar em outro método
    @Override
    @Nullable
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
                                                MethodArgumentTypeMismatchException ex, 
                                                HttpHeaders headers,
                                                HttpStatusCode status, 
                                                WebRequest request) {

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ExceptionInfo info = ExceptionInfo.builder()
        .dataHora(LocalDateTime.now())
        .detail(detail)
        .status(status.value())
        .title("Parametro inválido")
        .type("http://sgi.com.br/erro_de_negocio")
        .build();

        return handleExceptionInternal(ex, info, headers, status, request);
    }
}
