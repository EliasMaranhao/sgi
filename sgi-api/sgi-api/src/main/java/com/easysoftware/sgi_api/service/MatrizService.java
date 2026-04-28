package com.easysoftware.sgi_api.service;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.MatrizDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.dto.converters.FilialMapeador;
import com.easysoftware.sgi_api.dto.converters.MatrizMapper;
import com.easysoftware.sgi_api.dto.converters.MatrizMapeador;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.entities.Matriz;
import com.easysoftware.sgi_api.repository.MatrizRepository;

import jakarta.transaction.Transactional;

@Service
public class MatrizService {
    
    private final MatrizRepository matrizRepository;
    private final MatrizMapper mapeador;

    public MatrizService(MatrizRepository matrizRepository, MatrizMapper mapeador) {
        this.matrizRepository = matrizRepository;
        this.mapeador = mapeador;
    }

    @Transactional
    public Matriz salvar(Matriz matriz){
        return matrizRepository.save(matriz);
    }

    @Transactional
    public void excluir(Long id){
        Matriz m = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        matrizRepository.delete(m);
    }

    public List<MatrizResponseDTO> buscarMatrizTodas(){
        List<MatrizResponseDTO> dtos = new ArrayList<>();
        for(Matriz matriz : matrizRepository.findAll()){
            dtos.add(MatrizMapeador.entidadeParaDTO(matriz));
        }
        return dtos;
    }

    public MatrizResponseDTO buscarMatrizPeloId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não fooi localizado"));
        MatrizResponseDTO dto = MatrizMapeador.entidadeParaDTO(matriz);
        return dto;
    }

    public List<FilialResponseDTO> buscarFiliaisPeloMatrizId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado."));
        List<FilialResponseDTO> filiais = new ArrayList<>();
        if(matriz.getFiliais() != null){
            for(Filial filial : matriz.getFiliais()){
                filiais.add(FilialMapeador.entidadeParaDTO(filial));
            }
        }
        return filiais;
    }

    @Transactional
    public MatrizResponseDTO atualizar(Long id, MatrizDTO dto) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso nao foi localizado!"));
        mapeador.updateEntityFromDto(dto, matriz);
        matriz = matrizRepository.save(matriz);
        return mapeador.toDto(matriz);
    }
}
