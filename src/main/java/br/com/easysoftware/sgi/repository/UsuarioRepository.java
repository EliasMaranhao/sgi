package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.easysoftware.sgi.entity.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByUsername(String username);
}
