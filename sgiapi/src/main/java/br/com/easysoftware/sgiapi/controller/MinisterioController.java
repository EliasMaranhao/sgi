package br.com.easysoftware.sgiapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.model.Ministerio;
import br.com.easysoftware.sgiapi.repository.MinisterioRepository;
import br.com.easysoftware.sgiapi.service.MinisterioService;

@RestController
@RequestMapping("/ministerios")
public class MinisterioController {
    
    @Autowired
    private MinisterioService ministerioService;

    @Autowired
    private MinisterioRepository ministerioRepository;

    @GetMapping
    public ResponseEntity<List<Ministerio>> todos(){
        return ResponseEntity.ok(ministerioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Ministerio> salvar(@RequestBody Ministerio ministerio){
        Ministerio salvo = ministerioService.salvar(ministerio);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ministerio> buscarPeloId(@PathVariable Long id){
        Ministerio ministerio = ministerioService.buscarPorId(id);
        return ResponseEntity.ok(ministerio);
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Long id){
        ministerioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Ministerio> atualizar(@PathVariable Long id, @RequestBody Ministerio ministerio){
        Ministerio atualizado = ministerioService.atualizar(id, ministerio);
        return ResponseEntity.ok(atualizado);
    }
}
