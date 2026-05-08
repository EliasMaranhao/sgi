package com.easysoftware.sgi_api.dto.converters;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.easysoftware.sgi_api.dto.UsuarioDTO;
import com.easysoftware.sgi_api.dto.UsuarioResponseDTO;
import com.easysoftware.sgi_api.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    @Mapping(target = "filialId", source = "filial.id")
    @Mapping(target = "nomeFilial", source = "filial.nome")
    UsuarioResponseDTO toDto(Usuario usuario);

    @Mapping(target = "senha", source = "senha")
    @Mapping(target = "filial.id", source = "filialId")
    Usuario toEntity(UsuarioDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // O ID nunca deve ser alterado
    void updateEntityFromDto(UsuarioDTO dto, @MappingTarget Usuario usuario);
}
