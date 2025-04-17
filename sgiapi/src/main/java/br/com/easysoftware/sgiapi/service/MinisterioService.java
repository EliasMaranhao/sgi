package br.com.easysoftware.sgiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.easysoftware.sgiapi.entities.Ministerio;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeEmUsoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MinisterioNaoEncontradoException;
import br.com.easysoftware.sgiapi.repository.MinisterioRepository;

@Service
public class MinisterioService {
    
    @Autowired
    private MinisterioRepository ministerioRepository;

     @Transactional
    public Ministerio salvar(Ministerio ministerio){
        return ministerioRepository.save(ministerio);
    }

    public Ministerio buscarPorId(Long id){
        return buscar(id);
    }

    @Transactional
    public void remover(Long id){
        Ministerio ministerio = buscar(id);
        try {
            ministerioRepository.delete(ministerio);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Este recurso está em uso e não pode ser removido");
        }
    }

    @Transactional
    public Ministerio atualizar(Long id, Ministerio ministerio){
        Ministerio atual = buscar(id);
        BeanUtils.copyProperties(ministerio, atual, "id");
        return ministerioRepository.save(atual);
    }

    private Ministerio buscar(Long id){
        Ministerio ministerio = ministerioRepository.findById(id).orElseThrow(() -> new MinisterioNaoEncontradoException(id));
        return ministerio;
    }
}
