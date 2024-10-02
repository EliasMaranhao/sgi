package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.dto.mapper.ContatoDTOMapper;
import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
import br.com.easysoftware.sgi.repository.ContatoRepository;
import br.com.easysoftware.sgi.service.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService{

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ContatoDTOMapper contatoDTOMapper;

    @Override
    public List<ContatoDTO> salvar(Contato contato) { 
        Contato salvo = contatoRepository.save(contato);
        return buscarContatoPeloMembro(salvo.getMembro());
    }

    @Override
    public List<ContatoDTO> buscarContatoPeloMembro(Membro membro) {

        List<Contato> contatos = contatoRepository.findByMembro(membro);
        List<ContatoDTO> contatos_dto = null;

        if(!contatos.isEmpty()){
            contatos_dto = contatos.stream().map(contatoDTOMapper).collect(Collectors.toList());
        }
        return contatos_dto;
    }

    @Override
    public void deletar(Long id) {
        Optional<Contato> optional = contatoRepository.findById(id);

        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("O recurso buscado não foi encontrado");
        }

        contatoRepository.delete(optional.get());
    }
    
}
