package br.com.easysoftware.sgiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgiapi.model.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>{
    
}
