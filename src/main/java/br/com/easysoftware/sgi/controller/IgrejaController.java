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

import br.com.easysoftware.sgi.entity.Igreja;
import br.com.easysoftware.sgi.service.IgrejaService;

@RestController
@RequestMapping("/sgi/igreja")
public class IgrejaController {
    
    @Autowired
    private IgrejaService igrejaService;

    @GetMapping
    public ResponseEntity<List<Igreja>> buscar(){
        List<Igreja> igrejas = igrejaService.buscar();
        return ResponseEntity.ok(igrejas);
    }

    @PostMapping
    public ResponseEntity<Igreja> salvar(@RequestBody Igreja igreja){
        return ResponseEntity.status(HttpStatus.CREATED).body(igrejaService.salvar(igreja));
    }

    @GetMapping("/filiais/{id}")
    public ResponseEntity<List<Igreja>> buscarFiliais(@PathVariable Long id){
        return ResponseEntity.ok(igrejaService.buscarFiliais(id));
    }
}
