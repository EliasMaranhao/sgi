package com.easysoftware.sgi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easysoftware.sgi_api.entities.Matriz;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Long>{
    
}
