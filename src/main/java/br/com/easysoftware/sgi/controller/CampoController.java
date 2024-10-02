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

import br.com.easysoftware.sgi.entity.Campo;
import br.com.easysoftware.sgi.entity.Igreja;
import br.com.easysoftware.sgi.service.CampoService;

@RestController
@RequestMapping("sgi/campo")
public class CampoController {
    
    @Autowired
    private CampoService campoService;

    @PostMapping
    public ResponseEntity<Campo> salvar(@RequestBody Campo campo){
        Campo salvo = campoService.salvar(campo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/filiais/{id}")
    public ResponseEntity<List<Igreja>> buscarFiliais(@PathVariable Long id){
        return ResponseEntity.ok(campoService.buscarFiliais(id));
    }
}
