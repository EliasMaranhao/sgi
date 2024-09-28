package br.com.easysoftware.sgi.dto;
import java.util.List;

public record MembroDTO(String nome,
                        String dataConversao,
                        String dataBatismo,
                        String dataNascimento,
                        String recebidoEm,
                        String igrejaOrigem,
                        String genero,
                        String igreja,
                        List<ContatoDTO> contatos,
                        List<CargoDTO> cargos,
                        List<ParenteDTO> parentes) {


}
