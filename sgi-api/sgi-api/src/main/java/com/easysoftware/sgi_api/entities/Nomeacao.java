package com.easysoftware.sgi_api.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Essa entidade destina-se a informações de nomeações de cargos nos diversos departamentos 
 * da igreja
 */
@Entity
@Table(name = "nomeacao")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Nomeacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = true) // <--- Opcional!
    private Departamento departamento;

    @Column(name = "data_posse")
    private LocalDate dataPosse;

    @Column(name = "data_saida")
    private LocalDate dataSaida;
}
