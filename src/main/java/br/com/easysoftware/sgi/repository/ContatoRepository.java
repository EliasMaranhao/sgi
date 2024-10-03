package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Contato;
import br.com.easysoftware.sgi.entity.Membro;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
    List<Contato> findByMembro(Membro membro);
}
