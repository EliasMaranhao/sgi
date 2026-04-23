package com.easysoftware.sgi_api.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.easysoftware.sgi_api.dto.MembroDTO;
import com.easysoftware.sgi_api.dto.MembroResponseDTO;
import com.easysoftware.sgi_api.entities.Membro;
import com.easysoftware.sgi_api.service.MembroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/membros")
@CrossOrigin(origins = "http://localhost:4200") // Permite o acesso do seu Angular
public class MembroController {
    
    private final MembroService membroService;

    public MembroController(MembroService membroService){
        this.membroService = membroService;
    }

    @GetMapping
    public ResponseEntity<List<Membro>> listarTodos() {
        return ResponseEntity.ok(membroService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<MembroResponseDTO> cadastrar(@Valid @ModelAttribute MembroDTO dto) {
        // Usamos @ModelAttribute porque o formulário envia campos de texto e arquivos misturados
        MembroResponseDTO novoMembro = membroService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMembro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> buscarPorId(@PathVariable Long id) {
        MembroResponseDTO dto = membroService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
}
