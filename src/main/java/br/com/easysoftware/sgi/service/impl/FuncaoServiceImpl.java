package br.com.easysoftware.sgi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Funcao;
import br.com.easysoftware.sgi.repository.FuncaoRepository;
import br.com.easysoftware.sgi.service.FuncaoService;

@Service
public class FuncaoServiceImpl implements FuncaoService{

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Override
    public List<Funcao> getFuncoes() {
        return funcaoRepository.findAll();
    }
    
}
