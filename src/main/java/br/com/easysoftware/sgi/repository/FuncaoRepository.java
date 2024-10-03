package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long>{
    
}
