package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.dto.mapper.CargoDTOMapper;
import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.repository.CargosRepository;
import br.com.easysoftware.sgi.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargosRepository cargosRepository;

    @Autowired
    private CargoDTOMapper cargoDTOMapper;

    @Override
    public List<CargoDTO> salvar(Cargo cargo) {
        Cargo salvo = cargosRepository.save(cargo);
        return buscarCargosPorMembro(salvo.getMembro());
    }

    @Override
    public List<CargoDTO> getCargos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCargos'");
    }

    @Override
    public List<CargoDTO> buscarCargosPorMembro(Membro membro) {
        List<Cargo> cargos = cargosRepository.findByMembro(membro);
        List<CargoDTO> cargos_dto = null;

        if(!cargos.isEmpty()){
            cargos_dto = cargos.stream().map(cargoDTOMapper).collect(Collectors.toList());
        }
        return cargos_dto;
    }
    
}
