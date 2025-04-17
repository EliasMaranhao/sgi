package br.com.easysoftware.sgiapi.dto.input;

import br.com.easysoftware.sgiapi.entities.ParentescoEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ParenteInput {
    
    private ParentescoEnum parentescoEnum;
    private String nome;
}
