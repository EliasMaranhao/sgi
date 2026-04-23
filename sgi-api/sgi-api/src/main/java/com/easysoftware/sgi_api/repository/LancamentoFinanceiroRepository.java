package com.easysoftware.sgi_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.LancamentoFinanceiro;

@Repository
public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long>{
    
    // Busca lançamentos de uma igreja em um intervalo de tempo
    List<LancamentoFinanceiro> findByFilialIdAndDataMovimentoBetween(
        Long filialId, 
        LocalDateTime inicio, 
        LocalDateTime fim
    );
}
