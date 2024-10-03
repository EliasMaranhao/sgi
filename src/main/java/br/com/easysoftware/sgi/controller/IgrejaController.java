package br.com.easysoftware.sgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.IgrejaDTO;
import br.com.easysoftware.sgi.entity.Igreja;
import br.com.easysoftware.sgi.service.IgrejaService;


@RestController
@RequestMapping("/sgi/igreja")
public class IgrejaController {
    
    @Autowired
    private IgrejaService igrejaService;


    @PostMapping
    public ResponseEntity<Igreja> salvar(@RequestBody Igreja igreja){
        return ResponseEntity.status(HttpStatus.CREATED).body(igrejaService.salvar(igreja));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Igreja> buscarPeloId(@PathVariable Long id){
        Igreja igreja = igrejaService.buscarPeloId(id);
        return ResponseEntity.ok(igreja);
    }

    @GetMapping
    public ResponseEntity<List<IgrejaDTO>> buscarIgrejas() {
        List<IgrejaDTO> igrejas = igrejaService.buscarIgrejas();
        return ResponseEntity.ok(igrejas);
    }
    
}
