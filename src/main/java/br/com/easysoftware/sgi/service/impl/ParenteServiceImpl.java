package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.ParenteDTO;
import br.com.easysoftware.sgi.dto.mapper.ParenteDTOMapper;
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

    @Autowired
    private ParenteDTOMapper parenteDTOMapper;

    @Override
    public List<ParenteDTO> salvar(Parente parente) {
        Membro membro = membroService.buscarPeloNome(parente.getNome());

        if(membro == null){
            throw new RuntimeException("Membro não encontrado.");
        }

        Parente salvo = parenteRepository.save(parente);
        List<ParenteDTO> parentes = buscarParentePeloMembro(salvo.getMembro());

        return parentes;
    }

    @Override
    public List<ParenteDTO> buscarParentePeloMembro(Membro membro) {
        List<Parente> parentes = parenteRepository.findByMembro(membro);
        List<ParenteDTO> parentes_dto = null;

        if(!parentes.isEmpty()){
            parentes_dto = parentes.stream().map(parenteDTOMapper).collect(Collectors.toList());
        }
        return parentes_dto;
    }
    
}
