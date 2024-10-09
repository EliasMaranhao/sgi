package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.repository.membro.MembroRepositoryQuery;


@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>, MembroRepositoryQuery{
    
    Membro findByNome(String nome);

}
