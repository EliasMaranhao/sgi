package br.com.easysoftware.sgiapi.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.MembroNaoEncontradoException;
import br.com.easysoftware.sgiapi.model.Membro;
import br.com.easysoftware.sgiapi.model.Parente;
import br.com.easysoftware.sgiapi.repository.MembroRepository;

@Service
public class MembroService {
    
    @Autowired
    private MembroRepository membroRepository;

    public Membro buscarPorId(Long id){
        return buscar(id);
    }

    private Membro buscar(Long id){
        Membro membro = membroRepository.findById(id).orElseThrow(() -> new MembroNaoEncontradoException(String.format("O membro de id %s não foi localizado.", id, null), null));
        return membro;
    }

    public List<Parente> buscarParentes(Membro membro){
        return buscar(membro.getId()).getParentes();
    }

    public Membro salvar(Membro membro){
        return membroRepository.save(membro);
    }

    public Membro atualizar(Long id, Membro membro){
        Membro membroAtual = buscar(id);
        BeanUtils.copyProperties(membro, membroAtual, "id", "parentes", "endereco", "dataCadastro");
        membroAtual = membroRepository.save(membroAtual);
        return membroAtual;
    }

    public Membro atualizarParcial(Long id, Map<String, Object> campos){
        Membro membroAtual = buscar(id);
        merge(campos, membroAtual);
        return atualizar(id, membroAtual);
    }

	@SuppressWarnings("null")
    private void merge(Map<String, Object> camposOrigem, Membro membroDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Membro membroOrigem = objectMapper.convertValue(camposOrigem, Membro.class);

		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Membro.class, nomePropriedade);
            field.setAccessible(true);
            Object novaValor = ReflectionUtils.getField(field, membroOrigem);

            ReflectionUtils.setField(field, membroDestino, novaValor);
		});
	}
}
