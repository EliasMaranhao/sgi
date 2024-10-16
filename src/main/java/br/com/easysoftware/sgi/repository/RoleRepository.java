package br.com.easysoftware.sgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.easysoftware.sgi.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findRoleByName(String name);
}
