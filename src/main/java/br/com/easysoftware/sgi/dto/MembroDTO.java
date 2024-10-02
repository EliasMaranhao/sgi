package br.com.easysoftware.sgi.dto;

import br.com.easysoftware.sgi.entity.Documento;
import br.com.easysoftware.sgi.entity.Endereco;
import br.com.easysoftware.sgi.entity.EstadoCivil;
import br.com.easysoftware.sgi.entity.Genero;
import br.com.easysoftware.sgi.entity.SituacaoMembro;

import java.time.LocalDate;
import java.util.List;

public record MembroDTO(Long id,
                        String nome,
                        LocalDate dataNascimento,
                        LocalDate dataConversao,
                        LocalDate dataBatismo,
                        LocalDate dataRecebido,
                        String igrejaOrigem,
                        EstadoCivil estadoCivil,
                        SituacaoMembro situacaoMembro,
                        Boolean veioOutraIgreja,
                        Boolean veioOutroCampo,
                        String campoOrigem,
                        Long igreja,
                        List<ContatoDTO> contatos,
                        List<ParenteDTO> parentes,
                        Documento documento,
                        Genero genero,
                        Endereco endereco,
                        List<CargoDTO> cargos) {
    

}
