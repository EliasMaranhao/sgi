package br.com.easysoftware.sgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.dto.ParenteDTO;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.entity.Parente;
import br.com.easysoftware.sgi.service.ParenteService;

@RestController
@RequestMapping("/sgi/parente")
public class ParenteController {
    
    @Autowired
    private ParenteService parenteService;

    @PostMapping
    public ResponseEntity<List<ParenteDTO>> salvar(@RequestBody Parente parente){
        List<ParenteDTO> parentes = parenteService.salvar(parente);
        return ResponseEntity.status(HttpStatus.CREATED).body(parentes);
    }

    @PostMapping("/membro")
    public ResponseEntity<List<ParenteDTO>> buscarParentePorMembro(@RequestBody Membro membro){
        List<ParenteDTO> parentes = parenteService.buscarParentePeloMembro(membro);
        return ResponseEntity.ok(parentes);
    }
}
