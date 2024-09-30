package br.com.easysoftware.sgi.dto;
import java.time.LocalDate;
import java.util.List;

import br.com.easysoftware.sgi.entity.Documento;
import br.com.easysoftware.sgi.entity.Endereco;
import br.com.easysoftware.sgi.entity.EstadoCivil;
import br.com.easysoftware.sgi.entity.Genero;
import br.com.easysoftware.sgi.entity.SituacaoMembro;

public record MembroDTO(
                        Long id,
                        String nome,
                        LocalDate dataConversao,
                        LocalDate dataBatismo,
                        LocalDate dataNascimento,
                        LocalDate recebidoEm,
                        String igrejaOrigem,
                        EstadoCivil estadoCivil,
                        SituacaoMembro situacaoMembro,
                        Boolean veioOutraIgreja,
                        Boolean veioOutroCampo,
                        String campoOrigem,
                        String igreja,
                        Documento documento,
                        Genero genero,
                        Endereco endereco,
                        List<ContatoDTO> contatos,
                        List<CargoDTO> cargos,
                        List<ParenteDTO> parentes) {


}
