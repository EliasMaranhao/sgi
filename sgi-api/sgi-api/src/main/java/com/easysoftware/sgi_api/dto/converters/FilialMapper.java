package com.easysoftware.sgi_api.dto.converters;

import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.entities.Contato;
import com.easysoftware.sgi_api.entities.Endereco;
import com.easysoftware.sgi_api.entities.Filial;

public class FilialMapper {
    
    public static FilialResponseDTO entidadeParaDTO(Filial filial){
        
        Endereco endereco = filial.getEndereco();
        Contato contato = filial.getContato();
        MatrizResponseDTO dto = null;

        if(filial.getMatriz() != null){
            dto = MatrizMapper.entidadeParaDTO(filial.getMatriz());
        }
       
        return new FilialResponseDTO(filial.getId(), filial.getNome(), filial.getPastorDirigente(), filial.getDataInauguracao(), endereco, contato, filial.getCnpj(), dto);
    }
}
