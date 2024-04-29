package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static in.co.gorest.utils.Reutilizaveis.createUser;

public class DeletandoUsuario extends BaseTest {
    @Test
    public void deletarSemToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "UserSemToken");
        params.put("gender", "male");
        params.put("email", "testeUserSemToken@ggmail.com");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + "")
                .body(params)
                .when()
                .delete("v2/users/"+897998)
                .then()
                .statusCode(404);




    }
    @Test
    public void deletarSemPath() {
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "UserSemToken");
        params.put("gender", "male");
        params.put("email", "testeUserSemToken@ggmail.com");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .delete("v2/users/")
                .then()
                .statusCode(404);




    }
    @Test
    public void deletarComSucesso() {
        Map<String, Object> usercreated = createUser("TestandoDelete");
        Integer idDeletar = (Integer) usercreated.get("id");

        Map<String, Object> params = new HashMap<>();
        params.put("nome", "UserSemToken");
        params.put("gender", "male");
        params.put("email", "testeUserSemToken@ggmail.com");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .delete("v2/users/"+idDeletar)
                .then()
                .statusCode(204);




    }
}
