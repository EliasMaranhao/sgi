package br.com.easysoftware.sgiapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgiapi.dto.converters.IgrejaConverter;
import br.com.easysoftware.sgiapi.dto.input.IgrejaInput;
import br.com.easysoftware.sgiapi.dto.output.IgrejaOutput;
import br.com.easysoftware.sgiapi.entities.Igreja;
import br.com.easysoftware.sgiapi.repository.IgrejaRepository;
import br.com.easysoftware.sgiapi.service.IgrejaService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/igrejas")
public class IgrejaController {
    
    @Autowired
    private IgrejaService igrejaService;

    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private IgrejaConverter converter;

    @PostMapping
    public ResponseEntity<IgrejaOutput> salvar(@RequestBody IgrejaInput dto){
        Igreja igreja = converter.convertToEntity(dto);
        igreja = igrejaService.salvar(igreja);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToDTO(igreja));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<IgrejaOutput> atualizar(@PathVariable Long id, @RequestBody IgrejaInput dto){
        Igreja atual = igrejaService.buscarPorId(id);
        converter.copyToEntity(dto, atual);
        atual = igrejaService.salvar(atual);
        return ResponseEntity.ok(converter.convertToDTO(atual));
    }

    @GetMapping
    public ResponseEntity<List<IgrejaOutput>> todos(){
        List<IgrejaOutput> dtos = converter.toList(igrejaRepository.findAll());
        return ResponseEntity.ok(dtos);
    }
}
