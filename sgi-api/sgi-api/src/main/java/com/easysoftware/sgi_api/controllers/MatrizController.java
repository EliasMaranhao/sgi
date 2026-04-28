package com.easysoftware.sgi_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easysoftware.sgi_api.dto.FilialResponseDTO;
import com.easysoftware.sgi_api.dto.MatrizDTO;
import com.easysoftware.sgi_api.dto.MatrizResponseDTO;
import com.easysoftware.sgi_api.entities.Matriz;
import com.easysoftware.sgi_api.service.MatrizService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/matriz")
@CrossOrigin(origins = "http://localhost:4200")
public class MatrizController {
    
    private final AutenticacaoController autenticacaoController;
    private final MatrizService matrizService;

    public MatrizController(MatrizService matrizService, AutenticacaoController autenticacaoController) {
        this.matrizService = matrizService;
        this.autenticacaoController = autenticacaoController;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Matriz matriz){
        Matriz salvo = matrizService.salvar(matriz);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<MatrizResponseDTO>> buscarMatrizTodas(){
        List<MatrizResponseDTO> matrizes = matrizService.buscarMatrizTodas();
        return ResponseEntity.ok(matrizes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMatriz(@PathVariable Long id){
        matrizService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatrizResponseDTO> buscarMatrizPeloId(@PathVariable Long id){
        MatrizResponseDTO dto = matrizService.buscarMatrizPeloId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filiais/{id}")
    public ResponseEntity<List<FilialResponseDTO>> listarFiliais(@PathVariable Long id) {
        List<FilialResponseDTO> filiais = matrizService.buscarFiliaisPeloMatrizId(id);
        return ResponseEntity.ok(filiais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatrizResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MatrizDTO dto){
        MatrizResponseDTO matrizResponseDTO = matrizService.atualizar(id, dto);
        return ResponseEntity.ok(matrizResponseDTO);
    }
    
}
