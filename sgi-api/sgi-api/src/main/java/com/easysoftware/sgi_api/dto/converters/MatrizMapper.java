package com.easysoftware.sgi_api.dto.converters;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.easysoftware.sgi_api.dto.MatrizDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.entities.Matriz;

@Mapper(componentModel = "spring")
public interface MatrizMapper {
    
    MatrizResponseDTO toDto(Matriz matriz);
    Matriz toEntity(MatrizDTO matrizDto);

    // Método de atualização
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // O ID nunca deve ser alterado
    @Mapping(target = "filiais", ignore = true) // Geralmente atualizamos filiais em outro endpoint
    void updateEntityFromDto(MatrizDTO dto, @MappingTarget Matriz matriz);
}
