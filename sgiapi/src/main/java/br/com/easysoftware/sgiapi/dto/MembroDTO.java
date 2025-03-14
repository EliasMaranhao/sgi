package br.com.easysoftware.sgiapi.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MembroDTO {
    
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataBatismo;
    private LocalDate dataConversao;
}
