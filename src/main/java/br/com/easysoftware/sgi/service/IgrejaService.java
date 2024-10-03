package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.IgrejaDTO;
import br.com.easysoftware.sgi.entity.Igreja;

public interface IgrejaService {
    
    List<Igreja> buscar();
    Igreja salvar(Igreja igreja);
    Igreja buscarPeloId(Long id);
    List<IgrejaDTO> buscarIgrejas();
}
