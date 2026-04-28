package com.easysoftware.sgi_api.dto.converters;

import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.entities.Matriz;

public class MatrizMapeador {
    
    public static MatrizResponseDTO entidadeParaDTO(Matriz matriz){

        return new MatrizResponseDTO(matriz.getId() ,matriz.getNome(), matriz.getPastorPresidente(), matriz.getVicePresidente(), matriz.getDenominacao());
    }
}
