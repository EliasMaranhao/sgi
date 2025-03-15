package br.com.easysoftware.sgiapi.api;

import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import br.com.easysoftware.sgiapi.util.DatabaseCleaner;
import br.com.easysoftware.sgiapi.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class MembroApiTestIT {
    
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private String jsonCorretoCozinhaChinesa;

    @BeforeEach
    public void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/membros";

        databaseCleaner.clearTables();
        jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/criarMembro.json");
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarMembros(){
       
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornar14Objetos_QuandoConsultarMembros(){
        RestAssured
            .given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", Matchers.hasSize(14));
    }

    @Test
    public void deveRetornarObjetosEspecificos_QuandoConsultarMembros(){
        RestAssured
            .given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("nome", Matchers.hasItems("Elias Maranhão Antonio", "Carlos Antonio"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarMembro(){
        RestAssured
            .given()
                .body(jsonCorretoCozinhaChinesa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente(){
        RestAssured
            .given()
                .pathParam("membroID", 2)
                .accept(ContentType.JSON)
            .when()
                .get("/{membroID}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo("Carlos Antonio"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente(){
        RestAssured
            .given()
                .pathParam("membroID", 765)
                .accept(ContentType.JSON)
            .when()
                .get("/{membroID}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo("Carlos Antonio"));
    }
}
