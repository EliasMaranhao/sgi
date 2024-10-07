package br.com.easysoftware.sgi.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.entity.Cargo;

@Service
public class CargoDTOMapper implements Function<Cargo, CargoDTO>{

    @Override
    public CargoDTO apply(Cargo cargo) {
        return new CargoDTO(cargo.getFuncao(), 
                            cargo.getDataPosse(), 
                            cargo.getDataDestituicao());
    }
    
}
