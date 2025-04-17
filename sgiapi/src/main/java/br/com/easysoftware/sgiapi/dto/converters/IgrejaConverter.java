package br.com.easysoftware.sgiapi.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easysoftware.sgiapi.dto.input.IgrejaInput;
import br.com.easysoftware.sgiapi.dto.output.IgrejaOutput;
import br.com.easysoftware.sgiapi.entities.Igreja;

@Component
public class IgrejaConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public Igreja convertToEntity(IgrejaInput input){
        return modelMapper.map(input, Igreja.class);
    }

    public IgrejaOutput convertToDTO(Igreja igreja){
        return modelMapper.map(igreja, IgrejaOutput.class);
    }

    public void copyToEntity(IgrejaInput input, Igreja igreja){
        modelMapper.map(input, igreja);
    }

    public List<IgrejaOutput> toList(List<Igreja> igrejas){
        return igrejas.stream().map((igreja) -> convertToDTO(igreja)).collect(Collectors.toList());
    }
}
