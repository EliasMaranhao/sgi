package br.com.easysoftware.sgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.entity.Parente;

@Repository
public interface ParenteRepository extends JpaRepository<Parente, Long>{
    List<Parente> findByMembro(Membro membro);
}
