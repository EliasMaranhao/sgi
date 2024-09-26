package br.com.easysoftware.sgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.entity.Parente;
import br.com.easysoftware.sgi.repository.ParenteRepository;
import br.com.easysoftware.sgi.service.MembroService;
import br.com.easysoftware.sgi.service.ParenteService;

@Service
public class ParenteServiceImpl implements ParenteService{

    @Autowired
    private ParenteRepository parenteRepository;
    @Autowired
    private MembroService membroService;

    @Override
    public Parente salvar(Parente parente) {
        Membro membro = membroService.buscarPeloNome(parente.getNome());

        if(membro == null){
            throw new RuntimeException("Membro não encontrado.");
        }

        return parenteRepository.save(parente);
    }
    
}
