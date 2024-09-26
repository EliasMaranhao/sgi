package br.com.easysoftware.sgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.repository.ContatoRepository;
import br.com.easysoftware.sgi.service.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService{

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }
    
}
