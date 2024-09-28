package br.com.easysoftware.sgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.service.ContatoService;

import java.util.List;

@RestController
@RequestMapping("/sgi/contato")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> salvar(@RequestBody Contato contato){
        Contato salvo = contatoService.salvar(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/membro/{id}")
    public ResponseEntity<List<ContatoDTO>> getContatos(@PathVariable Long id){
        List<ContatoDTO> contatos = contatoService.buscarContatoPeloMembro(id);
        return ResponseEntity.ok(contatos);
    }
}
