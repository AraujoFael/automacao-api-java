package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

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
        // Criação do primeiro usuário
        Map<String, Object> paramsCreated1 = createUser("Testes1");
        String email1 = (String) paramsCreated1.get("email");

        // Criação do segundo usuário
        Map<String, Object> paramsCreated2 = createUser("Testes2");
        Integer idUsuario2 = (Integer) paramsCreated2.get("id");

        // Tentativa de editar o segundo usuário com o email do primeiro
        Map<String, Object> paramsPatch = new HashMap<>();
        paramsPatch.put("name", "Testando");
        paramsPatch.put("email", email1);
        paramsPatch.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(paramsPatch)
                .when()
                .patch("v2/users/" + idUsuario2)
                .then()
                .body("field", hasItem("email"))
                .body("message", hasItem("has already been taken"))
                .statusCode(422);
    }
    @Test
    public void requisicaoAlteracaoComSucesso(){
        Map<String, Object> paramsCreated2 = createUser("Testes2");
        Integer idUsuario2 = (Integer) paramsCreated2.get("id");
        String flexUser = retornaDataAtualEmString();

        // Tentativa de editar o segundo usuário com o email do primeiro
        Map<String, Object> paramsPatch = new HashMap<>();
        paramsPatch.put("name", "TestandoAlteração");
        paramsPatch.put("email", "alterandoemail"+flexUser+"@ggmail.com");
        paramsPatch.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(paramsPatch)
                .when()
                .patch("v2/users/" + idUsuario2)
                .then()
                .body("id", is(idUsuario2))
                .statusCode(200);
    }
    private Map createUser(String namePrefix) {
        String flexUser = retornaDataAtualEmString();
        Map<String, Object> params = new HashMap<>();
        params.put("name", namePrefix + flexUser);
        params.put("gender", "male");
        params.put("email", namePrefix + flexUser + "@gmail.com");
        params.put("status", "active");

        return given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .post("v2/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().as(Map.class);
    }
  // Ajustar para testar email já existente 
}
