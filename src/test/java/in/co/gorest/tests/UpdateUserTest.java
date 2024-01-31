package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.given;

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


}
