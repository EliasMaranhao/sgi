package br.com.easysoftware.sgiapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.easysoftware.sgiapi.entities.Endereco;
import br.com.easysoftware.sgiapi.entities.Igreja;
import br.com.easysoftware.sgiapi.entities.Membro;
import br.com.easysoftware.sgiapi.entities.Ministerio;
import br.com.easysoftware.sgiapi.service.MembroService;

@SpringBootTest
public class MembroServiceTestIT {
    
    @Autowired
    private MembroService membroService;

    private Ministerio ministerio;
    private Igreja igreja;

    @BeforeEach
    public void setup(){
        ministerio = new Ministerio();
        ministerio.setId(1l);

        igreja = new Igreja();
        igreja.setId(1l);
        igreja.setMinisterio(ministerio);
    }

    @Test
    public void deveRetornarObjeto_QuandoSalvarComDadosPreenchidosCorretamente(){
        Membro novo = new Membro();
        novo.setIgreja(igreja);
        novo.setNome("Jurandir da Silva");
        novo.setDataBatismo(LocalDate.of(2022, 4, 21));
        novo.setDataConversao(LocalDate.of(2002, 10, 22));
        novo.setDataNascimento(LocalDate.of(1987, 9, 12));

        Endereco endereco = new Endereco();
        endereco.setBairro("Engenho de Dentro");
        endereco.setCep("20745100");
        endereco.setCidade("Rio de Janeiro");
        endereco.setComplemento("apto 103, bloco 6");
        endereco.setEstado("Rio de Janeiro");
        endereco.setNumero(1255);
        endereco.setRua("Borja Reis");

        novo.setEndereco(endereco);

        Membro salvo = membroService.salvar(novo);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Jurandir da Silva", salvo.getNome());
    }

    @Test
    public void deveLancarExcecao_QuandoCadastrarSemNome(){
        Membro novo = new Membro();

        DataIntegrityViolationException erroEsperado =
        assertThrows(DataIntegrityViolationException.class, () -> {
            membroService.salvar(novo);
        });

        assertNotNull(erroEsperado);
    }
}
