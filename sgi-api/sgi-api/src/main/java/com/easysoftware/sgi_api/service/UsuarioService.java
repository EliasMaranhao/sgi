package com.easysoftware.sgi_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.domain.exception.BusinessException;
import com.easysoftware.sgi_api.dto.UsuarioDTO;
import com.easysoftware.sgi_api.dto.UsuarioResponseDTO;
import com.easysoftware.sgi_api.dto.converters.UsuarioMapper;
import com.easysoftware.sgi_api.entities.Filial;
import com.easysoftware.sgi_api.entities.Usuario;
import com.easysoftware.sgi_api.repository.FilialRepository;
import com.easysoftware.sgi_api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final FilialRepository filialRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper, FilialRepository filialRepository){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
        this.filialRepository = filialRepository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioDTO dto){

        Filial filial = filialRepository.findById(dto.filialId()).orElseThrow(() -> new BusinessException("Está filial nao foi encontrada"));

        if (usuarioRepository.findByLoginAndFilialId(dto.login(), dto.filialId()) != null) {
            throw new RuntimeException("Usuário já cadastrado nesta filial.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setFilial(filial);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioSalvo);
    }
}
