package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Campo;
import br.com.easysoftware.sgi.entity.Igreja;

import java.util.List;

@Repository
public interface IgrejaRepository extends JpaRepository<Igreja, Long>{
    List<Igreja> findByCampo(Campo campo);
}
