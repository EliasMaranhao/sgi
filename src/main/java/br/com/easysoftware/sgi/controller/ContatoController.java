package br.com.easysoftware.sgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.service.ContatoService;

import java.util.List;

@RestController
@RequestMapping("/sgi/contato")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<List<ContatoDTO>> salvar(@RequestBody Contato contato){
        List<ContatoDTO> contatos = contatoService.salvar(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatos);
    }

    @PostMapping("/membro")
    public ResponseEntity<List<ContatoDTO>> getContatos(@RequestBody Membro membro){
        List<ContatoDTO> contatos = contatoService.buscarContatoPeloMembro(membro);
        return ResponseEntity.ok(contatos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> editar(@PathVariable Long id, @RequestBody Contato contato){
        Contato salvo = contatoService.editar(id, contato);
        return ResponseEntity.ok(salvo);
    }
    
}
