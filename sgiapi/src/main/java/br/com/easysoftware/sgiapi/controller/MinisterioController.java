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

import br.com.easysoftware.sgiapi.dto.converters.MinisterioConverter;
import br.com.easysoftware.sgiapi.dto.input.MinisterioInput;
import br.com.easysoftware.sgiapi.dto.output.MinisterioOutput;
import br.com.easysoftware.sgiapi.entities.Ministerio;
import br.com.easysoftware.sgiapi.repository.MinisterioRepository;
import br.com.easysoftware.sgiapi.service.MinisterioService;

@RestController
@RequestMapping("/ministerios")
public class MinisterioController {
    
    @Autowired
    private MinisterioService ministerioService;

    @Autowired
    private MinisterioRepository ministerioRepository;

    @Autowired
    private MinisterioConverter converter;

    @GetMapping
    public ResponseEntity<List<MinisterioOutput>> todos(){
        List<MinisterioOutput> dtos = converter.toList(ministerioRepository.findAll());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<MinisterioOutput> salvar(@RequestBody MinisterioInput dto){
        Ministerio ministerio = converter.convertToEntity(dto);
        ministerio = ministerioService.salvar(ministerio);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToDTO(ministerio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MinisterioOutput> buscarPeloId(@PathVariable Long id){
        Ministerio ministerio = ministerioService.buscarPorId(id);
        return ResponseEntity.ok(converter.convertToDTO(ministerio));
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable Long id){
        ministerioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<MinisterioOutput> atualizar(@PathVariable Long id, @RequestBody MinisterioInput dtoInput){
        Ministerio atual = ministerioService.buscarPorId(id);
        converter.copyToEntity(dtoInput, atual);
        atual = ministerioService.salvar(atual);
        return ResponseEntity.ok(converter.convertToDTO(atual));
    }
}
