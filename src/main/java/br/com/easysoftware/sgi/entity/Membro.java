package br.com.easysoftware.sgi.entity;

import jakarta.persistence.Column;
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
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "membro")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Membro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_conversao")
    private LocalDate dataConversao;

    @NotNull
    @Column(name = "data_batismo")
    private LocalDate dataBatismo;

    @Column(name = "data_recebido")
    private LocalDate dataRecebido;

    @Column(name = "igreja_origem")
    private String igrejaOrigem;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_membro")
    private SituacaoMembro situacaoMembro;

    @Column(name = "veio_outra_igreja")
    private Boolean veioOutraIgreja;

    @Column(name = "veio_outro_campo")
    private Boolean veioOutroCampo;

    @Column(name = "campo_origem")
    private String campoOrigem;

    //@JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "igreja_id")
    private Igreja igreja;

    //@JsonManagedReference
    @JsonIgnore
    @OneToMany
    private List<Contato> contatos;

    @JsonManagedReference
    @OneToMany(mappedBy = "membro")
    private List<Parente> parentes;

    @Embedded
    private Documento documento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Embedded
    private Endereco endereco;

    @JsonManagedReference
    @OneToMany(mappedBy = "membro")
    List<Cargo> cargos;
}
