package br.com.easysoftware.sgiapi.dto.input;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MembroInput {
    
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataBatismo;
    private LocalDate dataConversao;
}
