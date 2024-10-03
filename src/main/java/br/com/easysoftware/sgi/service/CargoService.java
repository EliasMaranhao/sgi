package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.entity.Cargo;

public interface CargoService {
    Cargo salvar(Cargo cargo);
    List<CargoDTO> getCargos();
}
