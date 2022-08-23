package com.montanha.gerenciador.viagens;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ViagensTest {
    @Test
    public void DadoLogAdminQdoCadViagensStatus201(){
        //Configurar caminho comun da api


        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";
        //login na api com admin
        String token = given()
                .body("{\n" +
                "  \"email\": \"admin@email.com\",\n" +
                "  \"senha\": \"654321\"\n" +
                "}")
                .contentType(ContentType.JSON)
            .when()
                .post("/v1/auth")
            .then()
                .extract()
                    .path("data.token");
        //cadastrar a viagem
        given()
                .header("Authorization", token)
                .body("{\n" +
                        "    \"acompanhante\": \"beltrano\",\n" +
                        "    \"dataPartida\": \"2022-09-14\",\n" +
                        "    \"dataRetorno\": \"2022-10-14\",\n" +
                        "    \"localDeDestino\": \"Manaus\",\n" +
                        "    \"regiao\": \"Norte\"\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post("/v1/viagens")
        .then()
                .log().all()
                .assertThat()
                   .statusCode(201);


    }
}
