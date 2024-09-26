package br.com.easysoftware.sgi.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Parente;

@Service
public class ParenteDTOMapper implements Function<Parente, ParenteDTO>{

    @Override
    public ParenteDTO apply(Parente parente) {
       return new ParenteDTO(parente.getParentesco().name(), 
                             parente.getNome());
    }
    
}
