package br.com.easysoftware.sgiapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.model.Membro;
import br.com.easysoftware.sgiapi.model.Parente;
import br.com.easysoftware.sgiapi.service.MembroService;

@RestController
@RequestMapping("/membros")
public class MembroController {
    
    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    public ResponseEntity<Membro> buscarPorId(@PathVariable Long id){
        Membro membro = membroService.buscarPorId(id);
        return ResponseEntity.ok().body(membro);
    }

    @PostMapping
    public ResponseEntity<Membro> criar(@RequestBody Membro membro){
        Membro salvo = membroService.salvar(membro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/parentes")
    public ResponseEntity<List<Parente>> buscarParentes(@RequestBody Membro membro){
        List<Parente> parentes = membroService.buscarParentes(membro);
        return ResponseEntity.ok(parentes);
    }

   @PatchMapping("/{id}")
    public ResponseEntity<Membro> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
        Membro atualizado = membroService.atualizarParcial(id, campos);
        return ResponseEntity.ok().body(atualizado);
    }
}
