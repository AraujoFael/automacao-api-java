package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreaterUserTest extends BaseTest {
    @Test
    public void cadastrarSemToken() {
        given()
                .header("Authorization", "Bearer " + "")
                .body("{ \"name\": \"Nome do usuário\",\n" +
                        "\"gender\": \"Género do usuário\",\n" +
                        "\"email\": \"email do usuário\",\n" +
                        "\"status\": \"status do usuário\"}")
                .when()
                .post("v2/user")
                .then()
                .statusCode(404);


    }


    @Test
    public void validaBodyRequestSemNome() {


            given()
                    .header("Authorization", "Bearer " + APP_TOKEN)
                    .body("{ \"gender\": \"male\",\n" +
                            "\"email\": \"testandoSemNome@ggmail.com\",\n" +
                            "\"status\": \"active\"}")
                    .when()
                    .post("v2/users")
                    .then()
                    .statusCode(422)
                    .body("field", hasItem("name"))
                    .body("message", hasItem("can't be blank"));

    }



    @Test
    public void validaBodyRequestSemGender() {

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body("{ \"name\": \"userTest\",\n" +
                        "\"email\": \"teste@ggteste.com\",\n" +
                        "\"status\": \"active\"}")
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("gender"))
                .body("message", hasItem("can't be blank, can be male of female"));
    }



    @Test
    public void validaBodyRequestSemEmail() {

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body("{ \"name\": \"userTest\",\n" +
                        "\"gender\": \"male\",\n" +
                        "\"status\": \"active\"}")
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("email"))
                .body("message", hasItem("can't be blank"));
    }
    @Test
    public void validaBodyRequestSemStatus() {

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body("{ \"name\": \"userTest\",\n" +
                        "\"gender\": \"male\",\n" +
                        "\"email\": \"teste@ggmail.com\"}")
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("status"))
                .body("message", hasItem("can't be blank"));
    }
    @Test
    public void validaCreatedComSucesso(){
        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body("{ \"name\": \"Teste usuario\",\n" +
                        "\"gender\": \"male\",\n" +
                        "\"email\": \"testeusuarioo1@ggmail.com\",\n" +
                        "\"status\": \"active\"}")
                .when()
                .post("v2/users")
                .then()
                .statusCode(201);

    }


}