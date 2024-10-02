package br.com.easysoftware.sgi.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.entity.Contato;

@Service
public class ContatoDTOMapper implements Function<Contato, ContatoDTO>{

    @Override
    public ContatoDTO apply(Contato contato) {
       return new ContatoDTO(contato.getId(), contato.getTipoContato(), contato.getValor());
    }
    
}
