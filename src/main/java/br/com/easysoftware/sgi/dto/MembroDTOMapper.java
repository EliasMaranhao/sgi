package br.com.easysoftware.sgi.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Membro;

@Service
public class MembroDTOMapper implements Function<Membro, MembroDTO>{

    @Autowired
    private ContatoDTOMapper contatoDTOMapper;

    @Autowired
    private CargoDTOMapper cargoDTOMapper;

    @Autowired
    private ParenteDTOMapper parenteDTOMapper;

    @Override
    public MembroDTO apply(Membro membro) {
        return new MembroDTO(membro.getNome(), 
                             membro.getDataConversao(), 
                             membro.getDataBatismo(), 
                             membro.getDataNascimento(),
                             membro.getDataRecebido(),
                             membro.getIgrejaOrigem(),
                             membro.getGenero().name(),
                             membro.getIgreja().getNome(),
                             getContatos(membro),
                             getCargos(membro),
                             getParentes(membro));
    }
    
    private List<ParenteDTO> getParentes(Membro membro) {
        List<ParenteDTO> parentes = membro.getParentes().stream().map(parenteDTOMapper).collect(Collectors.toList());
        return parentes;
    }

    private List<ContatoDTO> getContatos(Membro membro){
        List<ContatoDTO> contatos = membro.getContatos().stream().map(contatoDTOMapper).collect(Collectors.toList());
        return contatos;
    }

    private List<CargoDTO> getCargos(Membro membro){
        List<CargoDTO> cargos = membro.getCargos().stream().map(cargoDTOMapper).collect(Collectors.toList());
        return cargos;
    }
}
