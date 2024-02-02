package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUserTest extends BaseTest {
    @Test
    public void cadastrarSemToken() {

        given()
                .header("Authorization", "Bearer " + "")
                .body("{ \"name\": \"Nome do usuário\",\n" +
                        "\"gender\": \"Género do usuário\",\n" +
                        "\"email\": \"email do usuário\",\n" +
                        "\"status\": \"status do usuário\"}")
                .when()
                .post("v2/user/")
                .then()
                .statusCode(404);


    }
    @Test
    public void requisicaoSemName(){
        Map<String, Object> params = new HashMap <>();
        params.put("name", "");
        params.put("email", "test@gamial.ccom");
        params.put("status", "active");

        given()
                .header("Authorization","Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .patch("v2/users/"+6135647)
                .then()
                .body("field", hasItem("name"))
                .body("message", hasItem("can't be blank"))
                .statusCode(422);
    }
    @Test
    public void requisicaoSemEmail(){
        Map<String, Object> params = new HashMap <>();
        params.put("name", "Testando");
        params.put("email", "");
        params.put("status", "active");

        given()
                .header("Authorization","Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .patch("v2/users/"+6135647)
                .then()
                .body("field", hasItem("email"))
                .body("message", hasItem("can't be blank"))
                .statusCode(422);
    }

    @Test
    public void requisicaoSemStatus(){
        Map<String, Object> params = new HashMap <>();
        params.put("name", "Testando");
        params.put("email", "teste@ggmail.com");
        params.put("status", "");

        given()
                .header("Authorization","Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .patch("v2/users/"+6135647)
                .then()
                .body("field", hasItem("status"))
                .body("message", hasItem("can't be blank"))
                .statusCode(422);
    }
    @Test
    public void requisicaoEmailEmUso(){
        for(int j= 0 ; j <= 1 ; j++) {
            Map<String,Object> paramsCreated = new HashMap<String, Object>();
            String flexUser = retornaDataAtualEmString();
            String emailEmUso = "";

            paramsCreated.put("name","Testes");
            paramsCreated.put("gender", "male");
            paramsCreated.put("email","testuse"+flexUser+"@gmail.com");
            paramsCreated.put("status", "active");

    emailEmUso =  given()
                    .header("Authorization", "Bearer " + APP_TOKEN)
                    .body(paramsCreated)
                    .when()
                    .post("v2/users")
                    .then()
                    .statusCode(201)
                    .body("id", notNullValue())
            .extract().path("email");
            for(int h = 0 ; h <= 0; h++){
                Map<String,Object> paramsCreated1 = new HashMap<String, Object>();
                String flexUser1 = retornaDataAtualEmString();
                String idUsuario = "";

                paramsCreated1.put("name","Testes");
                paramsCreated1.put("gender", "male");
                paramsCreated1.put("email","testuse"+flexUser1+"@gmail.com");
                paramsCreated1.put("status", "active");

                idUsuario =  given()
                        .header("Authorization", "Bearer " + APP_TOKEN)
                        .body(paramsCreated1)
                        .when()
                        .post("v2/users")
                        .then()
                        .statusCode(201)
                        .body("id", notNullValue())
                        .extract().path("id");

            for (int i = 0; i <= 1; i++) {
                Map<String, Object> params = new HashMap<>();
                params.put("name", "Testando");
                params.put("email", emailEmUso);
                params.put("status", "active");
                Integer idCovertido = Integer.valueOf(idUsuario);

                given()
                        .header("Authorization", "Bearer " + APP_TOKEN)
                        .body(params)
                        .when()
                        .patch("v2/users/" + idCovertido)
                        .then()
                        .body("field", hasItem("email"))
                        .body("message", hasItem("can't be blank"))
                        .statusCode(500);
            }
            }
        }
    }
  // Ajustar para testar email já existente 
}
