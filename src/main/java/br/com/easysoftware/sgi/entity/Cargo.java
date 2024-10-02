package br.com.easysoftware.sgi.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cargo {
    
    @EmbeddedId
    private CargoFuncaoId id;

    @MapsId("funcaoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "funcao_id")
    private Funcao funcao;

    @JsonBackReference
    @MapsId("membroId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "membro_id")
    private Membro membro;

    @Column(name = "data_posse")
    private LocalDate dataPosse;

    @Column(name = "data_destituicao")
    private LocalDate dataDestituicao;

}
