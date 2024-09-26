package br.com.easysoftware.sgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.repository.CargosRepository;
import br.com.easysoftware.sgi.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargosRepository cargosRepository;

    @Override
    public Cargo salvar(Cargo cargo) {
        return cargosRepository.save(cargo);
    }
    
}
