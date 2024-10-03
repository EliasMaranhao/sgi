package br.com.easysoftware.sgi.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Igreja;

@Service
public class IgrejaDTOMapper implements Function<Igreja, IgrejaDTO>{

    @Override
    public IgrejaDTO apply(Igreja igreja) {
        return new IgrejaDTO(igreja.getId(), 
                      igreja.getNome(), 
                      igreja.getEndereco(), 
                      igreja.getCampo().getId(), 
                      igreja.getDenominacao());
    }
    
}
