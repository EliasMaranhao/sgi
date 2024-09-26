package br.com.easysoftware.sgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.service.CargoService;

@RestController
@RequestMapping("/sgi/cargo")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<Cargo> salvar(@RequestBody Cargo cargo){
        Cargo salvo = cargoService.salvar(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

}
