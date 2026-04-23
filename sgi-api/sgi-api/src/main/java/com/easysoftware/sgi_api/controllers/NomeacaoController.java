package com.easysoftware.sgi_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easysoftware.sgi_api.entities.Nomeacao;
import com.easysoftware.sgi_api.repository.NomeacaoRepository;
import com.easysoftware.sgi_api.service.NomeacaoService;

@RestController
@RequestMapping("/api/nomeacao")
@CrossOrigin(origins = "http://localhost:4200")
public class NomeacaoController {
    
    private final NomeacaoRepository nomeacaoRepository;
    private final NomeacaoService nomeacaoService;

    public NomeacaoController(NomeacaoRepository nomeacaoRepository, 
                              NomeacaoService nomeacaoService){
        
        this.nomeacaoRepository = nomeacaoRepository;
        this.nomeacaoService = nomeacaoService;
    }

    // Busca o histórico de um membro para a mat-table do Angular
    @GetMapping("/membro/{membroId}")
    public ResponseEntity<List<Nomeacao>> historicoPorMembro(@PathVariable Long membroId) {
        return ResponseEntity.ok(nomeacaoRepository.findByMembroId(membroId));
    }

    // Endpoint para nomear um membro (vincular a cargo/departamento)
    @PostMapping("/nomear")
    public ResponseEntity<Nomeacao> nomearMembro(@RequestBody Nomeacao nomeacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nomeacaoRepository.save(nomeacao));
    }

    // Endpoint para encerrar um mandato (preencher dataSaida)
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrarMandato(@PathVariable Long id) {
        nomeacaoService.encerrarCargo(id);
        return ResponseEntity.noContent().build();
    }
}
