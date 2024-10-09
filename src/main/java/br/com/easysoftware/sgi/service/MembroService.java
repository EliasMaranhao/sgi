package br.com.easysoftware.sgi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.easysoftware.sgi.dto.MembroDTO;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.repository.filter.MembroFilter;

public interface MembroService {
    Membro salvar(Membro membro);
    MembroDTO buscarPorId(Long id);
    Membro buscarPeloNome(String nome);
    Membro atualizar(Long id, Membro membro);
    List<Membro> buscarMembros();
    Membro buscarMembroPorId(Long id);
    Page<Membro> filtrar(MembroFilter membroFilter, Pageable pageable);
}
