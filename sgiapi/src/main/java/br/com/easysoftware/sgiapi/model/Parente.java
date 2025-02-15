package br.com.easysoftware.sgiapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean membrezia;

    @Enumerated(EnumType.STRING)
    private ParentescoEnum parentesco;

    private String celular;

    private String telefone;

    private String email;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexoEnum sexo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GrauInstrucaoEnum grauInstrucao;

    private String profissao;

    @Column(nullable = false)
    private boolean moraNaMesmaCasa;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
