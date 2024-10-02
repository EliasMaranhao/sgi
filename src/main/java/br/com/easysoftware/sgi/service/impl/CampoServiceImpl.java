package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.entity.Campo;
import br.com.easysoftware.sgi.entity.Igreja;
import br.com.easysoftware.sgi.exception.CampoNaoExisteException;
import br.com.easysoftware.sgi.repository.CampoRepository;
import br.com.easysoftware.sgi.service.CampoService;

@Service
public class CampoServiceImpl implements CampoService{

    @Autowired
    private CampoRepository campoRepository;

    @Override
    public Campo salvar(Campo campo) {
        return campoRepository.save(campo);
    }

    @Override
    public List<Igreja> buscarFiliais(Long id) {
        Campo campo = buscarPeloId(id);
        return campo.getIgrejas();
    }

    @Override
    public Campo buscarPeloId(Long id) {
        Optional<Campo> optional = campoRepository.findById(id);

        if(optional.isEmpty()){
            throw new CampoNaoExisteException("O recurso buscaodo não existe");
        }

        return optional.get();
    }
    
}
