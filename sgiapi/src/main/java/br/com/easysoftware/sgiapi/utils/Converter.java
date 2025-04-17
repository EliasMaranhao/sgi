package br.com.easysoftware.sgiapi.utils;

public interface Converter {
    Object convertToEntity(Object object); 
    Object convertToDTO(Object object);
}
