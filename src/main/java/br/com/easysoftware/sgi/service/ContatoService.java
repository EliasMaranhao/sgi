package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.entity.Contato;

public interface ContatoService {
    Contato salvar(Contato contato);
    List<ContatoDTO> buscarContatoPeloMembro(Long id);
}
