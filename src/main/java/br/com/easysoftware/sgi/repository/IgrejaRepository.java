package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.easysoftware.sgi.entity.Campo;
import br.com.easysoftware.sgi.entity.Igreja;

import java.util.List;

public interface IgrejaRepository extends JpaRepository<Igreja, Long>{
    List<Igreja> findByCampo(Campo campo);
}
