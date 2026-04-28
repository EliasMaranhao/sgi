package com.easysoftware.sgi_api.controllers;

import java.util.List;

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

import com.easysoftware.sgi_api.dto.FilialDTO;
import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.repository.FilialRepository;
import com.easysoftware.sgi_api.service.FilialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/filial")
public class FilialController {
    
    private final FilialService filialService;
    private final FilialRepository filialRepository;

    public FilialController(FilialService filialService, FilialRepository filialRepository) {
        this.filialService = filialService;
        this.filialRepository = filialRepository;
    }

    @PostMapping
    public ResponseEntity<FilialResponseDTO> salvar(@RequestBody Filial filial){
        FilialResponseDTO salvo = filialService.salvar(filial);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> buscarFilialPeloId(@PathVariable Long id){
        FilialResponseDTO dto = filialService.buscarFilialPeloId(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        filialService.excluirFilial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Filial>> listar(){
        List<Filial> filiais = filialRepository.findAll();
        return ResponseEntity.ok(filiais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FilialDTO dto) {
        FilialResponseDTO dtoAtualizado = filialService.atualizarFilial(id, dto);
        return ResponseEntity.ok(dtoAtualizado);
    }

}
