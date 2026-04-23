package com.easysoftware.sgi_api.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.entities.Nomeacao;
import com.easysoftware.sgi_api.repository.NomeacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class NomeacaoService {
    
    private final NomeacaoRepository nomeacaoRepository;

    public NomeacaoService(NomeacaoRepository nomeacaoRepository){
        this.nomeacaoRepository = nomeacaoRepository;
    }

    @Transactional
    public void encerrarCargo(Long nomeacaoId) {
        Nomeacao nomeacao = nomeacaoRepository.findById(nomeacaoId)
            .orElseThrow(() -> new BusinessException("Nomeação não encontrada."));
        
        nomeacao.setDataSaida(LocalDate.now());
        nomeacaoRepository.save(nomeacao);
    }
}
