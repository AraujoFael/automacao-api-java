package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.core.Constantes.APP_TOKEN;
import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
public class ListUserTest extends BaseTest{
    @Test
    public void cadastrarToken() {
       /* Map<String, Object> params = new HashMap<>();
        params.put("nome", "UserSemToken");
        params.put("gender", "male");
        params.put("email", "testeUserSemToken@ggmail.com");
        params.put("status", "active");*/

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .when()
                .get("v2/users")
                .then()
                .statusCode(404);


        // ver no gorest se está correto o endPoint

    }
}
