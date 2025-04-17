package br.com.easysoftware.sgiapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.easysoftware.sgiapi.entities.Parente;
import br.com.easysoftware.sgiapi.repository.ParenteRepository;

@Service
public class ParenteService {
    
    @Autowired
    private ParenteRepository parenteRepository;

     @Transactional
    public Parente criar(Parente parente){
        return parenteRepository.save(parente);
    }

    public List<Parente> listar(){
        return parenteRepository.findAll();
    }
}
