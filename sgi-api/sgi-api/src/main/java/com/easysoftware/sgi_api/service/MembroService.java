package com.easysoftware.sgi_api.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.domain.exception.MembroNaoEncontradoException;
import com.easysoftware.sgi_api.dto.MembroDTO;
import com.easysoftware.sgi_api.dto.MembroResponseDTO;
import com.easysoftware.sgi_api.dto.converters.MembroMappers;
import com.easysoftware.sgi_api.entities.Membro;
import com.easysoftware.sgi_api.entities.StatusMembro;
import com.easysoftware.sgi_api.repository.FilialRepository;
import com.easysoftware.sgi_api.repository.MembroRepository;

import jakarta.transaction.Transactional;

@Service
public class MembroService {
    
    private final MembroRepository membroRepository;
    private final FileService fileService;
    private final FilialRepository filialRepository;

    public MembroService(MembroRepository membroRepository, FileService fileService, FilialRepository filialRepository){
        this.membroRepository = membroRepository;
        this.fileService = fileService;
        this.filialRepository = filialRepository;
    }

    @Transactional
    public MembroResponseDTO cadastrar(MembroDTO dto) {

        String nomeArquivo = null;
        if (dto.fotoUrl() != null && !dto.fotoUrl().isEmpty()) {
            nomeArquivo = fileService.uploadFile(dto.fotoUrl(), "membros");
        }

        Membro membro = new Membro();
        membro.setCpf(dto.cpf());
        membro.setDataBatismo(dto.dataBatismo());
        membro.setEndereco(dto.endereco());

        membro.setFilial(filialRepository.findById(dto.filial_id()).orElseThrow(() -> new BusinessException("Filial não foi localizada")));
        membro.setFotoUrl(nomeArquivo);
        membro.setNome(dto.nome());
        membro.setStatus(dto.status());

        // Regra: Não permitir CPFs duplicados
        if (membroRepository.existsByCpf(membro.getCpf())) {
            throw new BusinessException("Já existe um membro cadastrado com este CPF.");
        }
        
        // Regra: Todo novo membro começa como 'ATIVO' por padrão se não definido
        if (membro.getStatus() == null) {
            membro.setStatus(StatusMembro.ATIVO);
        }
        
        Membro salvo = membroRepository.save(membro);

        return MembroMappers.entidadeParaDTO(salvo);
    }

    public @Nullable List<Membro> listarTodos() {
        return membroRepository.findAll();
    }

    public @Nullable MembroResponseDTO buscarPorId(Long id) {
        Membro membro = membroRepository.findById(id).orElseThrow(() -> new MembroNaoEncontradoException("Membro não localizado"));
         return MembroMappers.entidadeParaDTO(membro);
    }
}
