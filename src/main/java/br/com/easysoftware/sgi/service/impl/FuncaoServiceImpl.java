package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Funcao;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
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

    @Override
    public Funcao buscarPorId(Long id) {
        Funcao funcao = buscarFuncao(id);
        return funcao;
    }
    
    private Funcao buscarFuncao(Long id){
        Optional<Funcao> optional = funcaoRepository.findById(id);

        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("O recurso buscado não foi localizado");
        }

        return optional.get();
    }
}
