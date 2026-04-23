package com.easysoftware.sgi_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long>{
    
    Optional<Filial> findByCnpj(String cnpj);
}
