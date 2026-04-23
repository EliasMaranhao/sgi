package com.easysoftware.sgi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    /**
     * O Spring Security utiliza este método para localizar o usuário 
     * durante o processo de autenticação (loadUserByUsername).
     * O nome do campo no banco deve ser o que você definiu na @Column(name = "usuario").
     */
    UserDetails findByLogin(String login);
}
