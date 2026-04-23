package com.easysoftware.sgi_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Membro;
import com.easysoftware.sgi_api.entities.StatusMembro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>{

    // Lista todos os membros de uma congregação específica
    List<Membro> findByFilialId(Long filialId);
    
    // Busca membros por status (ex: buscar apenas os ATIVOS)
    List<Membro> findByFilialIdAndStatus(Long filialId, StatusMembro status);

    boolean existsByCpf(String cpf);
}
