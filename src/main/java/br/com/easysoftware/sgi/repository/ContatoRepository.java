package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.easysoftware.sgi.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
}
