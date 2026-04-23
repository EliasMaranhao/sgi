package com.easysoftware.sgi_api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.entities.LancamentoFinanceiro;
import com.easysoftware.sgi_api.repository.LancamentoFinanceiroRepository;

import jakarta.transaction.Transactional;

@Service
public class FinanceiroService {
    
    private final LancamentoFinanceiroRepository financeiroRepository;

    public FinanceiroService(LancamentoFinanceiroRepository financeiroRepository){
        this.financeiroRepository = financeiroRepository;
    }

    @Transactional
    public LancamentoFinanceiro registrarLancamento(LancamentoFinanceiro lancamento) {
        // Regra: Impedir lançamentos com data futura (opcional, dependendo da igreja)
        if (lancamento.getDataMovimento().isAfter(LocalDateTime.now())) {
            throw new BusinessException("Não é possível registrar um lançamento com data futura.");
        }

        // Regra: Valor deve ser sempre positivo
        if (lancamento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor do lançamento deve ser maior que zero.");
        }

        return financeiroRepository.save(lancamento);
    }
}
