package com.easysoftware.sgi_api.dto.converters;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.easysoftware.sgi_api.dto.FilialDTO;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.entities.Filial;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    
    FilialResponseDTO toDto(Filial filial);
    Filial toEntity(FilialDTO filialDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // O ID nunca deve ser alterado
    @Mapping(target = "membros", ignore = true) // Geralmente atualizamos filiais em outro endpoint
    void updateEntityFromDto(FilialDTO dto, @MappingTarget Filial filial);
}
