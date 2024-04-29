package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.utils.Reutilizaveis.createUser;
import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CriacaoDeUsuario extends BaseTest {

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
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","");
        params.put("gender", "male");
        params.put("email","teste@ggmail.com");
        params.put("status", "active");


            given()
                    .header("Authorization", "Bearer " + APP_TOKEN)
                    .body(params)
                    .when()
                    .post("v2/users")
                    .then()
                    .statusCode(422)
                    .body("field", hasItem("name"))
                    .body("message", hasItem("can't be blank"));

    }



    @Test
    public void validaBodyRequestSemGender() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","Testes");
        params.put("gender", "");
        params.put("email","teste@ggmail.com");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("gender"))
                .body("message", hasItem("can't be blank, can be male of female"));
    }



    @Test
    public void validaBodyRequestSemEmail() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","Testes");
        params.put("gender", "male");
        params.put("email","");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("email"))
                .body("message", hasItem("can't be blank"));
    }
    @Test
    public void validaBodyRequestSemStatus() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","Testes");
        params.put("gender", "male");
        params.put("email","testuse@gmail.com");
        params.put("status", "");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .post("v2/users")
                .then()
                .statusCode(422)
                .body("field", hasItem("status"))
                .body("message", hasItem("can't be blank"));
    }
    @Test
    public void validaCreatedComSucesso(){
        Map<String,Object> params = new HashMap<String, Object>();
        String flexUser = retornaDataAtualEmString();

        params.put("name","Testes");
        params.put("gender", "male");
        params.put("email","testuse"+flexUser+"@gmail.com");
        params.put("status", "active");

                 given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .post("v2/users")
                .then()
                .statusCode(201)
                         .body("id", notNullValue());



    }
    @Test
    public void validaUsuarioJaCadastrado(){
        Map<String, Object> usercreated = createUser("TestandoDelete");
        String emailJaExistente = (String) usercreated.get("email");

            Map<String,Object> params = new HashMap<String, Object>();
            String flexUser = retornaDataAtualEmString();

                params.put("name","Testes");
                params.put("gender", "male");
                params.put("email",emailJaExistente);
                params.put("status", "active");

                given()
                        .header("Authorization", "Bearer " + APP_TOKEN)
                        .body(params)
                        .when()
                        .post("v2/users")
                        .then()
                        .statusCode(422)
                        .body("field", hasItem("email"))
                        .body("message", hasItem("has already been taken"));


    }



    }



