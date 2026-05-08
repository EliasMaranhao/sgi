package com.easysoftware.sgi_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.easysoftware.sgi_api.dto.UsuarioDTO;
import com.easysoftware.sgi_api.dto.UsuarioResponseDTO;
import com.easysoftware.sgi_api.dto.converters.UsuarioMapper;
import com.easysoftware.sgi_api.entities.Usuario;
import com.easysoftware.sgi_api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioDTO dto){

        if (usuarioRepository.findByLoginAndFilialId(dto.login(), dto.filialId()) != null) {
            throw new RuntimeException("Usuário já cadastrado nesta filial.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioSalvo);
    }
}
