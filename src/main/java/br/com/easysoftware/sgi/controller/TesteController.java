package br.com.easysoftware.sgi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("sgi/teste")
public class TesteController {
    
    @GetMapping
    public ResponseEntity<LocalDate> getData(){
        return ResponseEntity.ok(LocalDate.now());
    }
}
