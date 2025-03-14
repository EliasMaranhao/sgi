package br.com.easysoftware.sgiapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeEmUsoException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.EntidadeNaoEncontradaException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.NegocioException;
import br.com.easysoftware.sgiapi.exceptionhandler.exceptions.IgrejaNaoEncontradaException;
import br.com.easysoftware.sgiapi.model.Igreja;
import br.com.easysoftware.sgiapi.model.Ministerio;
import br.com.easysoftware.sgiapi.repository.IgrejaRepository;

@Service
public class IgrejaService {
    
    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private MinisterioService ministerioService;

    public Igreja salvar(Igreja igreja){
        Ministerio ministerio = igreja.getMinisterio();
        ministerio = ministerioService.buscarPorId(ministerio.getId());

        igreja.setMinisterio(ministerio);
        return igrejaRepository.save(igreja);
    }

    public Igreja buscarPorId(Long id){
        Igreja igreja = buscar(id);
        return igreja;
    }

    public void remover(Long id){
        Igreja igreja = buscar(id);

        try {
            igrejaRepository.delete(igreja);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Este recurso está em uso e não pode ser removido");
        }
    }

    public Igreja atualizar(Long id, Igreja igreja){
        Igreja atual = buscar(id);
        BeanUtils.copyProperties(igreja, atual, "id");

        try {
            salvar(atual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException("Erro ao tentar atualizar recursos, verifique os valores enviados", null);
        }
        return atual;
    }

    private Igreja buscar(Long id){
        Igreja igreja = igrejaRepository.findById(id).orElseThrow(() -> new IgrejaNaoEncontradaException(id));
        return igreja;
    }
}
