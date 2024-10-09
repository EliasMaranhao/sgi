package br.com.easysoftware.sgi.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembroFilter {
    
    private String nome;
    private LocalDate dataConversaoDe;
    private LocalDate dataConversaoDeAte;
    private LocalDate dataBatismoDe;
    private LocalDate dataBatismoDeAte;


}
