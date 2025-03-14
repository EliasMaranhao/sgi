package br.com.easysoftware.sgiapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.model.Igreja;
import br.com.easysoftware.sgiapi.repository.IgrejaRepository;
import br.com.easysoftware.sgiapi.service.IgrejaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/igrejas")
public class IgrejaController {
    
    @Autowired
    private IgrejaService igrejaService;

    @Autowired
    private IgrejaRepository igrejaRepository;

    @PostMapping
    public ResponseEntity<Igreja> salvar(@RequestBody Igreja igreja){
        Igreja salvo = igrejaService.salvar(igreja);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Igreja> atualizar(@PathVariable Long id, @RequestBody Igreja igreja){
        Igreja atualizado = igrejaService.atualizar(id, igreja);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Igreja>> todos(){
        List<Igreja> igrejas = igrejaRepository.findAll();
        return ResponseEntity.ok(igrejas);
    }
}
