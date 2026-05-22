package com.easysoftware.sgi_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.MatrizDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.dto.converters.FilialMapper;
import com.easysoftware.sgi_api.dto.converters.MatrizMapper;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.entities.Matriz;
import com.easysoftware.sgi_api.repository.MatrizRepository;

import jakarta.transaction.Transactional;

@Service
public class MatrizService {
    
    private final MatrizRepository matrizRepository;
    private final MatrizMapper matrizMapper;
    private final FilialMapper filialMapper;
    private final TenantManagementService tenantManagementService;

    public MatrizService(MatrizRepository matrizRepository, MatrizMapper matrizMapper, FilialMapper filialMapper, TenantManagementService tenantManagementService) {
        this.matrizRepository = matrizRepository;
        this.matrizMapper = matrizMapper;
        this.filialMapper = filialMapper;
        this.tenantManagementService = tenantManagementService;
    }

    @Transactional
    public Matriz salvar(Matriz matriz){
        // Gera o identificador único do schema baseado no nome ou CNPJ
        String tenantId = "tenant_" + matriz.getTenantId().replaceAll("\\D", "");
        matriz.setSchemaName(tenantId);

        Matriz salva = matrizRepository.save(matriz);
        tenantManagementService.createNewTenantSchema(tenantId);

        return salva;
    }

    @Transactional
    public void excluir(Long id){
        Matriz m = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado"));
        matrizRepository.delete(m);
    }

    public List<MatrizResponseDTO> buscarMatrizTodas(){
        List<MatrizResponseDTO> dtos = new ArrayList<>();
        for(Matriz matriz : matrizRepository.findAll()){
            dtos.add(matrizMapper.toDto(matriz));
        }
        return dtos;
    }

    public MatrizResponseDTO buscarMatrizPeloId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não fooi localizado"));
        MatrizResponseDTO dto = matrizMapper.toDto(matriz);
        return dto;
    }

    public List<FilialResponseDTO> buscarFiliaisPeloMatrizId(Long id) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso não foi localizado."));
        List<FilialResponseDTO> filiais = new ArrayList<>();
        if(matriz.getFiliais() != null){
            for(Filial filial : matriz.getFiliais()){
                filiais.add(filialMapper.toDto(filial));
            }
        }
        return filiais;
    }

    @Transactional
    public MatrizResponseDTO atualizar(Long id, MatrizDTO dto) {
        Matriz matriz = matrizRepository.findById(id).orElseThrow(() -> new BusinessException("Este recurso nao foi localizado!"));
        matrizMapper.updateEntityFromDto(dto, matriz);
        matriz = matrizRepository.save(matriz);
        return matrizMapper.toDto(matriz);
    }
}
