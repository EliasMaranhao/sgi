package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.ContatoDTO;
import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.entity.Membro;

public interface ContatoService {
    List<ContatoDTO> salvar(Contato contato);
    List<ContatoDTO> buscarContatoPeloMembro(Membro membro);
    void deletar(Long id);
}
