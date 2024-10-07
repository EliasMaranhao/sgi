package br.com.easysoftware.sgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.service.MembroService;

@RestController
@RequestMapping("/sgi/membro")
public class MembroController {
    
    @Autowired
    private MembroService membroService;

    @PostMapping
    public ResponseEntity<Membro> salvar(@Validated @RequestBody Membro membro){
        Membro salvo = membroService.salvar(membro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroDTO> buscarPorId(@PathVariable Long id){
        MembroDTO membro = membroService.buscarPorId(id);
        return ResponseEntity.ok(membro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> atualizar(@PathVariable Long id,@Validated @RequestBody Membro membro){
        Membro atualizado = membroService.atualizar(id, membro);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Membro>> buscarMembros(){
        return ResponseEntity.ok(membroService.buscarMembros());
    }
}
