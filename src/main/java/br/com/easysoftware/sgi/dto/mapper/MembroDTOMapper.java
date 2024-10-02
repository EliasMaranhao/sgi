package br.com.easysoftware.sgi.dto.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.dto.ParenteDTO;
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
       return new MembroDTO(membro.getId(), 
                            membro.getNome(), 
                            membro.getDataNascimento(), 
                            membro.getDataConversao(), 
                            membro.getDataBatismo(), 
                            membro.getDataRecebido(), 
                            membro.getIgrejaOrigem(), 
                            membro.getEstadoCivil(), 
                            membro.getSituacaoMembro(), 
                            membro.getVeioOutraIgreja(), 
                            membro.getVeioOutroCampo(), 
                            membro.getCampoOrigem(), 
                            membro.getIgreja().getId(), 
                            getContatos(membro), 
                            getParentes(membro), 
                            membro.getDocumento(), 
                            membro.getGenero(), 
                            membro.getEndereco(), 
                            getCargos(membro));
    }
    
    private List<ContatoDTO> getContatos(Membro membro){
        return membro.getContatos().stream().map(contatoDTOMapper).collect(Collectors.toList());
    }

    private List<CargoDTO> getCargos(Membro membro){
        return membro.getCargos().stream().map(cargoDTOMapper).collect(Collectors.toList());
    }

    private List<ParenteDTO> getParentes(Membro membro){
        return membro.getParentes().stream().map(parenteDTOMapper).collect(Collectors.toList());
    }
}
