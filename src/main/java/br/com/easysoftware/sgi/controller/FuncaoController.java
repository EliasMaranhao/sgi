package br.com.easysoftware.sgi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easysoftware.sgi.entity.Funcao;
import br.com.easysoftware.sgi.service.FuncaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/sgi/funcao")
public class FuncaoController {

    @Autowired
    private FuncaoService funcaoService;

    @GetMapping
    public ResponseEntity<List<Funcao>> getFuncoes() {
        List<Funcao> funcoes = funcaoService.getFuncoes();
        return ResponseEntity.ok(funcoes);
    }
    
}
