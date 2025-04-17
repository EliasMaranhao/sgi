package br.com.easysoftware.sgiapi.dto.input;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.easysoftware.sgiapi.entities.Igreja;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinisterioInput {
    
    @Valid
    private Long id;

    @NotNull
    @NotBlank 
    private String nome;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotNull
    private LocalDateTime dataAtualizacao;

    @NotNull
    @NotBlank
    private String presidente;
    private List<Igreja> igrejas;

    @NotNull
    @NotBlank
    private String vicePresidente;

    @NotNull
    private LocalDate dataInauguracao;
}
