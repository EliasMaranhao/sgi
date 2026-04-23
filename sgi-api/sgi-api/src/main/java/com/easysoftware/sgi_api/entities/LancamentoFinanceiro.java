package com.easysoftware.sgi_api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "lancamento_financeiro")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LancamentoFinanceiro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_movimento")
    private LocalDateTime dataMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMovimentacao tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaFinanceira categoria;
    
    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
}
