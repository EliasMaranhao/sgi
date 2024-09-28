package br.com.easysoftware.sgi.dto;
import java.time.LocalDate;
import java.util.List;

public record MembroDTO(String nome,
                        LocalDate dataConversao,
                        LocalDate dataBatismo,
                        LocalDate dataNascimento,
                        LocalDate recebidoEm,
                        String igrejaOrigem,
                        String genero,
                        String igreja,
                        List<ContatoDTO> contatos,
                        List<CargoDTO> cargos,
                        List<ParenteDTO> parentes) {


}
