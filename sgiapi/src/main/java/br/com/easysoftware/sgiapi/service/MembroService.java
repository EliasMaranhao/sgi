package br.com.easysoftware.sgiapi.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easysoftware.sgiapi.entities.Membro;
import br.com.easysoftware.sgiapi.entities.Parente;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeEmUsoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MembroNaoEncontradoException;
import br.com.easysoftware.sgiapi.repository.MembroRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class MembroService {
    
    @Autowired
    private MembroRepository membroRepository;

    public List<Membro> listar(){
        return membroRepository.findAll();
    }

    public Membro buscarPorId(Long id){
        return buscar(id);
    }

    private Membro buscar(Long id){
        Membro membro = membroRepository.findById(id).orElseThrow(() -> new MembroNaoEncontradoException(id));
        return membro;
    }

    public List<Parente> buscarParentes(Membro membro){
        return buscar(membro.getId()).getParentes();
    }

    @Transactional
    public Membro salvar(Membro membro){
        return membroRepository.save(membro);
    }

    @Transactional
    public Membro atualizar(Long id, Membro membro){
        Membro membroAtual = buscar(id);
        BeanUtils.copyProperties(membro, membroAtual, "id", "parentes", "endereco", "dataCadastro");
        membroAtual = membroRepository.save(membroAtual);
        return membroAtual;
    }

    @Transactional
    public Membro atualizarParcial(Long id, Map<String, Object> campos, HttpServletRequest request){
        Membro membroAtual = buscar(id);
        merge(campos, membroAtual, request);
        return atualizar(id, membroAtual);
    }

	@SuppressWarnings("null")
    private void merge(Map<String, Object> camposOrigem, Membro membroDestino, HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    
            Membro membroOrigem = objectMapper.convertValue(camposOrigem, Membro.class);
    
            camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Membro.class, nomePropriedade);
                field.setAccessible(true);
                Object novaValor = ReflectionUtils.getField(field, membroOrigem);
    
                ReflectionUtils.setField(field, membroDestino, novaValor);
            }); 
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }

	}

    @Transactional
    public void remover(Long id){   
        Membro membro = buscar(id);
        try {
            membroRepository.delete(membro);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Este recurso está em uso e não pode ser deletado");
        }
        
    }
}
