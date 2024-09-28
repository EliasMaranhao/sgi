package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.dto.ContatoDTOMapper;
import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.exception.MembroNaoEncontradoException;
import br.com.easysoftware.sgi.repository.ContatoRepository;
import br.com.easysoftware.sgi.repository.MembroRepository;
import br.com.easysoftware.sgi.service.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService{

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ContatoDTOMapper contatoDTOMapper;

    @Override
    public Contato salvar(Contato contato) {
        Optional<Membro> optional = membroRepository.findById(contato.getMembro().getId());

        if(optional.isEmpty()){
            throw new MembroNaoEncontradoException("O recurso buscado não foi encontrado");
        }

        return contatoRepository.save(contato);
    }

    @Override
    public List<ContatoDTO> buscarContatoPeloMembro(Long id) {
        Optional<Membro> optional = membroRepository.findById(id);
        Membro salvo = null;

        if(optional.isEmpty()){
            throw new MembroNaoEncontradoException("O recurso buscado não foi encontrado");
        }

        salvo = optional.get();

        List<ContatoDTO> contatos = contatoRepository.findByMembro(salvo).stream().map(contatoDTOMapper).collect(Collectors.toList());

        return contatos;
    }
    
}
