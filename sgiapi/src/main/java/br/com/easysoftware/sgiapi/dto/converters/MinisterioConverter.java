package br.com.easysoftware.sgiapi.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easysoftware.sgiapi.dto.input.MinisterioInput;
import br.com.easysoftware.sgiapi.dto.output.MinisterioOutput;
import br.com.easysoftware.sgiapi.entities.Ministerio;

@Component
public class MinisterioConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Ministerio convertToEntity(MinisterioInput input){
        return modelMapper.map(input, Ministerio.class);
    }

    public MinisterioOutput convertToDTO(Ministerio ministerio){
        return modelMapper.map(ministerio, MinisterioOutput.class);
    }

    public void copyToEntity(MinisterioInput input, Ministerio ministerio){
        modelMapper.map(input, ministerio);
    }

    public List<MinisterioOutput> toList(List<Ministerio> ministerios){
        return ministerios.stream().map((ministerio) -> convertToDTO(ministerio)).collect(Collectors.toList());
    }
}