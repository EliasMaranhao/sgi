package br.com.easysoftware.sgiapi.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MinisterioOutput {
    private Long id;
    private String nome;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private String presidente;
    private String vicePresidente;
    private LocalDate dataInauguracao;
}
