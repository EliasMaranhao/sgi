package com.easysoftware.sgi_api.service;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.converters.FilialMapper;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.repository.FilialRepository;

@Service
public class FilialService {
    
    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    public Filial salvar(Filial filial){
        return filialRepository.save(filial);
    }

    public FilialResponseDTO buscarFilialPeloId(Long id){
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado!"));
        FilialResponseDTO dto = FilialMapper.entidadeParaDTO(filial);
        return dto;
    }

    public void excluirFilial(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        filialRepository.delete(filial);
    }
}
