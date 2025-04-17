package br.com.easysoftware.sgiapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.entities.Cargo;
import br.com.easysoftware.sgiapi.repository.CargoRepository;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    
    @Autowired
    private CargoRepository cargoRepository;

    @PostMapping
    public ResponseEntity<Cargo> criar(@RequestBody Cargo cargo){
        Cargo salvo = cargoRepository.save(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
