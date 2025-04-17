package br.com.easysoftware.sgiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgiapi.entities.Ministerio;

@Repository
public interface MinisterioRepository extends JpaRepository<Ministerio, Long>{
    
}
