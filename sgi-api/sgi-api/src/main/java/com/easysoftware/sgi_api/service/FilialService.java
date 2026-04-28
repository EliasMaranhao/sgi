package com.easysoftware.sgi_api.service;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.FilialDTO;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.converters.FilialMapper;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.repository.FilialRepository;

import jakarta.transaction.Transactional;

@Service
public class FilialService {
    
    private final FilialRepository filialRepository;
    private final FilialMapper mapeador;

    public FilialService(FilialRepository filialRepository, FilialMapper mapeador) {
        this.filialRepository = filialRepository;
        this.mapeador = mapeador;
    }

    @Transactional
    public FilialResponseDTO salvar(Filial filial){
        Filial f = filialRepository.save(filial);
        return mapeador.toDto(f);
    }

    public FilialResponseDTO buscarFilialPeloId(Long id){
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado!"));
        //FilialResponseDTO dto = FilialMapeador.entidadeParaDTO(filial);
        return mapeador.toDto(filial);
    }

    @Transactional
    public void excluirFilial(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        filialRepository.delete(filial);
    }

    @Transactional
    public FilialResponseDTO atualizarFilial(Long id, FilialDTO dto){
        Filial filial = filialRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        mapeador.updateEntityFromDto(dto, filial);
        filial = filialRepository.save(filial);
        return mapeador.toDto(filial);
    }
}
