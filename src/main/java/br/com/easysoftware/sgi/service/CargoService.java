package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.CargoFuncaoId;
import br.com.easysoftware.sgi.entity.Membro;

public interface CargoService {
    List<CargoDTO> salvar(Cargo cargo);
    List<CargoDTO> getCargos();
    List<CargoDTO> buscarCargosPorMembro(Membro membro);
    Cargo editarCargo(Long membroId, Long funcaoId, Cargo cargo);
    Cargo buscarPorCodigo(CargoFuncaoId id);
    void deletarCargo(Long membroId, Long funcaoId);
}
