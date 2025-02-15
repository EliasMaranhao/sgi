package br.com.easysoftware.sgiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgiapi.model.Parente;

@Repository
public interface ParenteRepository extends JpaRepository<Parente, Long>{
    
}
