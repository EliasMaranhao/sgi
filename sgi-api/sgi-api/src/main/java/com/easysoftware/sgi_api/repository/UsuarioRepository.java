package com.easysoftware.sgi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT u FROM Usuario u " +
            "JOIN FETCH u.filial f " +
            "JOIN FETCH f.matriz m " +
            "WHERE u.login = :login")
    Usuario findByLogin(String login);

    UserDetails findByLoginAndFilialId(String login, Long filialId);
}
