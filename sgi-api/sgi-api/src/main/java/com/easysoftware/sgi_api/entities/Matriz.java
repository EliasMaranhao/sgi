package com.easysoftware.sgi_api.entities;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "matriz", schema = "public")
public class Matriz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    // Esse campo agora guarda o NOME DO SCHEMA que será criado dinamicamente no banco.
    // Mantivemos o tamanho 12 por segurança caso você decida colocar um prefixo como "schema_" antes dos 6 caracteres.
    @Column(name = "schema_name", length = 12, nullable = false, unique = true)
    private String schemaName;

    @Column(name = "pastor_presidente")
    private String pastorPresidente;

    @Column(name = "vice_presidente")
    private String vicePresidente;

    private String denominacao;

    @OneToMany(mappedBy = "matriz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Filial> filiais = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (this.schemaName == null) {
            // O nome do schema físico no banco precisa começar com letra e ser minúsculo
            this.schemaName = "sc_" + generateTenantCode().toLowerCase();
        }
    }

    private String generateTenantCode() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789"; // Alterado para minúsculas (boa prática para schemas)
        SecureRandom random = new SecureRandom();
        
        return random.ints(6, 0, characters.length())
                .mapToObj(characters::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
