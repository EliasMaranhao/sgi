package br.com.easysoftware.sgi.service;

import java.util.List;

import br.com.easysoftware.sgi.dto.LoginRequestDTO;
import br.com.easysoftware.sgi.dto.LoginResponseDTO;
import br.com.easysoftware.sgi.entity.Usuario;

public interface UsuarioService {
    
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    List<Usuario> listarTodos();
    void salvar(Usuario usuario);
}
