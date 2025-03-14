package br.com.easysoftware.sgiapi.dto;

import br.com.easysoftware.sgiapi.model.ParentescoEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ParenteDTO {
    
    private ParentescoEnum parentescoEnum;
    private String nome;
}
