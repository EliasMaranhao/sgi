package com.easysoftware.sgi_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Nomeacao;

@Repository
public interface NomeacaoRepository extends JpaRepository<Nomeacao, Long>{
    
// 1. Busca todo o histórico de cargos de um membro específico
    List<Nomeacao> findByMembroId(Long membroId);

    // 2. Busca apenas os cargos ATUAIS de um membro (onde a dataSaida é nula)
    List<Nomeacao> findByMembroIdAndDataSaidaIsNull(Long membroId);

    // 3. Busca todos os membros que ocupam um cargo específico em um departamento
    // Útil para saber: "Quem são os músicos do Departamento de Louvor?"
    List<Nomeacao> findByCargoIdAndDepartamentoIdAndDataSaidaIsNull(Long cargoId, Long departamentoId);

    // 4. Busca cargos "Institucionais" (onde o departamento é nulo)
    // Útil para listar Pastores ou Presbíteros da igreja sede
    List<Nomeacao> findByDepartamentoIsNullAndDataSaidaIsNull();
}
