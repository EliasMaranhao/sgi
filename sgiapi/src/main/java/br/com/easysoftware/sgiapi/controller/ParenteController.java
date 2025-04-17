package br.com.easysoftware.sgiapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.entities.Parente;
import br.com.easysoftware.sgiapi.service.ParenteService;

@RestController
@RequestMapping("/parentes")
public class ParenteController {
    
    @Autowired
    private ParenteService parenteService;

    @PostMapping
    public ResponseEntity<Parente> criar(@RequestBody Parente parente){
        Parente salvo = parenteService.criar(parente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Parente>> listar(){
        List<Parente> parentes = parenteService.listar();
        return ResponseEntity.ok(parentes);
    }
}
