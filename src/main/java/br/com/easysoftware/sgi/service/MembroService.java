package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.entity.Membro;

public interface MembroService {
    Membro salvar(Membro membro);
    MembroDTO buscarPorId(Long id);
    Membro buscarPeloNome(String nome);
    Membro atualizar(Long id, Membro membro);
    List<MembroDTO> buscarMembros();
}
