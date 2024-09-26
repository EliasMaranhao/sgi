package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.CargoFuncaoId;

public interface CargosRepository extends JpaRepository<Cargo, CargoFuncaoId>{
    
}
