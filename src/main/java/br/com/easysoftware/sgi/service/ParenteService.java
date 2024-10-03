package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.ParenteDTO;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.entity.Parente;

public interface ParenteService {
    List<ParenteDTO> salvar(Parente parente);
    List<ParenteDTO> buscarParentePeloMembro(Membro membro);
}
