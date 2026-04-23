package com.easysoftware.sgi_api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "matriz")
public class Matriz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Column(name = "pastor_presidente")
    private String pastorPresidente;

    @Column(name = "vice_presidente")
    private String vicePresidente;

    private String denominacao;

    @OneToMany(mappedBy = "matriz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Filial> filiais = new ArrayList<>();
}
