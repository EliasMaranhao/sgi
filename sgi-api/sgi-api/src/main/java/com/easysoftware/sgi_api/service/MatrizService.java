package com.easysoftware.sgi_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.dto.converters.FilialMapper;
import com.easysoftware.sgi_api.dto.converters.MatrizMapper;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.entities.Matriz;
import com.easysoftware.sgi_api.repository.MatrizRepository;

@Service
public class MatrizService {
    
    private final MatrizRepository matrizRepository;

    public MatrizService(MatrizRepository matrizRepository) {
        this.matrizRepository = matrizRepository;
    }

    public Matriz salvar(Matriz matriz){
        return matrizRepository.save(matriz);
    }

    public void excluir(Long id){
        Matriz m = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        matrizRepository.delete(m);
    }

    public List<MatrizResponseDTO> buscarMatrizTodas(){
        List<MatrizResponseDTO> dtos = new ArrayList<>();
        for(Matriz matriz : matrizRepository.findAll()){
            dtos.add(MatrizMapper.entidadeParaDTO(matriz));
        }
        return dtos;
    }

    public MatrizResponseDTO buscarMatrizPeloId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não fooi localizado"));
        MatrizResponseDTO dto = MatrizMapper.entidadeParaDTO(matriz);
        return dto;
    }

    public List<FilialResponseDTO> buscarFiliaisPeloMatrizId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado."));
        List<FilialResponseDTO> filiais = new ArrayList<>();
        if(matriz.getFiliais() != null){
            for(Filial filial : matriz.getFiliais()){
                filiais.add(FilialMapper.entidadeParaDTO(filial));
            }
        }
        return filiais;
    }
}
