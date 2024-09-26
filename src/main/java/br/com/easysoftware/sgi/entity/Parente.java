package br.com.easysoftware.sgi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parente")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoParentesco parentesco;
    
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "membro_id")
    private Membro membro;

}
