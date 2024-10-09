package br.com.easysoftware.sgi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.CargoDTO;
import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.CargoFuncaoId;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.service.CargoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/sgi/cargo")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<List<CargoDTO>> salvar(@RequestBody Cargo cargo){
        List<CargoDTO> salvos = cargoService.salvar(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }

    @GetMapping
    public String getCargos() {
        return new String();
    }
    
    @PostMapping("/membro")
    public ResponseEntity<List<CargoDTO>> buscarCargoPorMembro(@RequestBody Membro membro) {
        List<CargoDTO> cargos = cargoService.buscarCargosPorMembro(membro);
        return ResponseEntity.ok(cargos);
    }
    
    @PutMapping("/edit/{membroId}/{funcaoId}")
    public ResponseEntity<Cargo> editar(@PathVariable Long membroId, @PathVariable Long funcaoId, @RequestBody Cargo cargo) {
        Cargo salvo = cargoService.editarCargo(membroId, funcaoId, cargo);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{membroId}/{funcaoId}")
    public ResponseEntity<Cargo> buscarCargoPeloCodigo(@PathVariable Long membroId, @PathVariable Long funcaoId) {
        CargoFuncaoId id = new CargoFuncaoId(membroId, funcaoId);
        Cargo cargo = cargoService.buscarPorCodigo(id);
        return ResponseEntity.ok(cargo);
    }
    
    @DeleteMapping("/delete/{membroId}/{funcaoId}")
    public ResponseEntity<Void> deletarCargo(@PathVariable Long membroId, @PathVariable Long funcaoId){
        cargoService.deletarCargo(membroId, funcaoId);
        return ResponseEntity.noContent().build();
    }
}
