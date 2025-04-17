package br.com.easysoftware.sgiapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.entities.Funcao;
import br.com.easysoftware.sgiapi.repository.FuncaoRepository;

@RestController
@RequestMapping("/funcoes")
public class FuncaoController {
    
    @Autowired
    private FuncaoRepository funcaoRepository;

    @PostMapping
    public ResponseEntity<Funcao> criar(@RequestBody Funcao funcao){
        Funcao salvo = funcaoRepository.save(funcao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
