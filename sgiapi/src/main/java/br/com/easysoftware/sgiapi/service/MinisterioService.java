package br.com.easysoftware.sgiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeEmUsoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MinisterioNaoEncontradoException;
import br.com.easysoftware.sgiapi.model.Ministerio;
import br.com.easysoftware.sgiapi.repository.MinisterioRepository;

@Service
public class MinisterioService {
    
    @Autowired
    private MinisterioRepository ministerioRepository;

    public Ministerio salvar(Ministerio ministerio){
        return ministerioRepository.save(ministerio);
    }

    public Ministerio buscarPorId(Long id){
        return buscar(id);
    }

    public void remover(Long id){
        Ministerio ministerio = buscar(id);
        try {
            ministerioRepository.delete(ministerio);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Este recurso está em uso e não pode ser removido");
        }
    }

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
