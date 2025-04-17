package br.com.easysoftware.sgiapi.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.easysoftware.sgiapi.entities.Ministerio;
import br.com.easysoftware.sgiapi.entities.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IgrejaOutput {
    private Long id;
    private String nome;
    private LocalDate dataInauguracao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private Ministerio ministerio;
    private StatusEnum statusEnum;
}
