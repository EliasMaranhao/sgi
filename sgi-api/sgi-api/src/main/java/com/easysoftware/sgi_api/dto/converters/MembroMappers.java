package com.easysoftware.sgi_api.dto.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.easysoftware.sgi_api.dto.MembroResponseDTO;
import com.easysoftware.sgi_api.entities.Membro;

public class MembroMappers {
    
    public static MembroResponseDTO entidadeParaDTO(Membro membro){
        
        if (membro == null) return null;

        String nomeFilial = "";
        List<String> nomeacoes = new ArrayList<>();

        if(membro.getFilial() != null){
            nomeFilial = membro.getFilial().getNome();
        }

        // Tratando a Lista OneToMany (Nomeações)
        // Exemplo: extraindo apenas os nomes dos cargos das nomeações
        if (membro.getNomeacoes() != null) {
            nomeacoes = membro.getNomeacoes().stream()
                .map(nomeacao -> nomeacao.getCargo().getNome()) // Suposição de estrutura
                .collect(Collectors.toList());
        }

        MembroResponseDTO dto = new MembroResponseDTO(membro.getNome(), membro.getDataBatismo(), membro.getStatus(), membro.getFotoUrl(), nomeFilial, nomeacoes);

        return dto;
    }
}
