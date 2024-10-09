package br.com.easysoftware.sgi.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.dto.mapper.MembroDTOMapper;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.exception.MembroJaExisteException;
import br.com.easysoftware.sgi.exception.MembroNaoEncontradoException;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
import br.com.easysoftware.sgi.repository.MembroRepository;
import br.com.easysoftware.sgi.repository.filter.MembroFilter;
import br.com.easysoftware.sgi.service.MembroService;

@Service
public class MembroServiceImpl implements MembroService{

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private MembroDTOMapper membroDTOMapper;

    @Override
    public Membro salvar(Membro membro) {
        Membro consulta = membroRepository.findByNome(membro.getNome());

        if(consulta != null){
            throw new MembroJaExisteException("Este nome já existe na base de dados.");
        }

        Membro salvo = membroRepository.save(membro);
        return salvo;
    }

    @Override
    public MembroDTO buscarPorId(Long id) {
        //Optional<Membro> optional = membroRepository.findById(id).map(membroDTOMapper);
        Optional<MembroDTO> optional = membroRepository.findById(id).map(membroDTOMapper);

        if(optional.isEmpty()){
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }

        return optional.get();
    }

    @Override
    public Membro buscarPeloNome(String nome) {
        Membro membro = membroRepository.findByNome(nome);

        if(membro == null){
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }

       return membro;
    }

    @Override
    public Membro atualizar(Long id, Membro membro) {
        Optional<Membro> optional = membroRepository.findById(id);
        Membro salvo = null;

        if(optional.isEmpty()){
            throw new MembroNaoEncontradoException("Membro não encontrado.");
        }

        salvo = optional.get();
        BeanUtils.copyProperties(membro, salvo, "id");
        return membroRepository.save(salvo);
    }

    @Override
    public List<Membro> buscarMembros() {
        //List<MembroDTO> membros = membroRepository.findAll().stream().map(membroDTOMapper).collect(Collectors.toList());
        List<Membro> membros = membroRepository.findAll();
        return membros;
    }

    @Override
    public Membro buscarMembroPorId(Long id) {
        Membro membro = buscar(id);
        return membro;
    }

    private Membro buscar(Long id){
        Optional<Membro> optional = membroRepository.findById(id);

        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("O recurso buscado não foi localizado");
        }

        return optional.get();
    }

    @Override
    public Page<Membro> filtrar(MembroFilter membroFilter, Pageable pageable) {
        Page<Membro> membros = membroRepository.filtrar(membroFilter, pageable);
        //List<MembroDTO> membrosDTO = membros.stream().map(membroDTOMapper).collect(Collectors.toList());
        return membros;
    }
}
