package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easysoftware.sgi.entity.Cargo;
import br.com.easysoftware.sgi.entity.CargoFuncaoId;
import br.com.easysoftware.sgi.entity.Membro;

import java.util.List;


@Repository
public interface CargosRepository extends JpaRepository<Cargo, CargoFuncaoId>{
    List<Cargo> findByMembro(Membro membro);
}
