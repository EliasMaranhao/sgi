package br.com.easysoftware.sgi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "igreja")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Igreja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    public Long id;

    public String nome;

    @Embedded
    public Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "igreja_id")
    @JsonIgnore
    private Igreja igrejaPai;

    @OneToMany(mappedBy = "igrejaPai")
    private List<Igreja> igrejas;

    @Enumerated(EnumType.STRING)
    private Denominacao denominacao;

    @OneToMany(mappedBy = "igreja")
    private List<Membro> membros;
}
