package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.IgrejaDTO;
import br.com.easysoftware.sgi.dto.IgrejaDTOMapper;
import br.com.easysoftware.sgi.entity.Igreja;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
import br.com.easysoftware.sgi.repository.IgrejaRepository;
import br.com.easysoftware.sgi.service.IgrejaService;

import java.util.stream.Collectors;

@Service
public class IgrejaServiceImpl implements IgrejaService{

    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private IgrejaDTOMapper igrejaDTOMapper;

    @Override
    public List<Igreja> buscar() {
        return igrejaRepository.findAll();
    }

    @Override
    public Igreja salvar(Igreja igreja) {
        Igreja salva = igrejaRepository.save(igreja);
        return salva;
    }

    @Override
    public Igreja buscarPeloId(Long id){
        return buscar(id);
    }
    
    private Igreja buscar(Long id){
        Optional<Igreja> optional = igrejaRepository.findById(id);

        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("O recurso buscado não foi localizado");
        }

        return optional.get();
    }

    @Override
    public List<IgrejaDTO> buscarIgrejas() {
        List<IgrejaDTO> igrejas = igrejaRepository.findAll().stream().map(igrejaDTOMapper).collect(Collectors.toList());
        return igrejas;
    }
}
