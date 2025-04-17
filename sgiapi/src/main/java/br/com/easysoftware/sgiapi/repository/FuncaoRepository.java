package br.com.easysoftware.sgiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgiapi.entities.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long>{
    
}
