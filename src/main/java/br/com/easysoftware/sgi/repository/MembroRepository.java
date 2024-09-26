package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Membro;


@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>{
    
    Membro findByNome(String nome);

}
