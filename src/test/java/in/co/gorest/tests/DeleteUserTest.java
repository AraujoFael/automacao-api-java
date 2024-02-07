package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.core.Constantes.APP_TOKEN;
import static io.restassured.RestAssured.*;

public class DeleteUserTest extends BaseTest {
    @Test
    public void cadastrarSemToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "UserSemToken");
        params.put("gender", "male");
        params.put("email", "testeUserSemToken@ggmail.com");
        params.put("status", "active");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .body(params)
                .when()
                .delete("v2/users/"+897998)
                .then()
                .statusCode(404);

        // ver no gorest se está correto o endPoint
        kk // continuar no delete o problema era herança de classes


    }
}
