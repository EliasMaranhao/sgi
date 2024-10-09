package br.com.easysoftware.sgi.repository.membro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.repository.filter.MembroFilter;

public interface MembroRepositoryQuery {
    public Page<Membro> filtrar(MembroFilter membroFilter, Pageable pageable);
}
