package br.com.easysoftware.sgiapi.dto.input;

import java.time.LocalDateTime;

import br.com.easysoftware.sgiapi.entities.Ministerio;
import br.com.easysoftware.sgiapi.entities.StatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IgrejaInput {
    
    @Valid
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotNull
    private LocalDateTime dataAtualizacao;

    @NotNull
    private Ministerio ministerio;

    @NotNull
    private StatusEnum statusEnum;

}
