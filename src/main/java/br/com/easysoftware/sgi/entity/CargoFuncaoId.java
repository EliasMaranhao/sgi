package br.com.easysoftware.sgi.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CargoFuncaoId implements Serializable{
    
    @Column(name = "membro_id")
    @EqualsAndHashCode.Include
    private Long membroId;

    @Column(name = "funcao_id")
    @EqualsAndHashCode.Include
    private Long funcaoId;

}
