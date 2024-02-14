package in.co.gorest.tests;

import in.co.gorest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.core.Constantes.APP_TOKEN;
import static in.co.gorest.utils.Reutilizaveis.createUser;
import static in.co.gorest.utils.Reutilizaveis.retornaDataAtualEmString;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
public class ListUserTest extends BaseTest{
    @Test
    public void listagemDeSucesso() {
        Map<String, Object> usercreated = createUser("TestandoDelete");
        Integer idProcurar = (Integer) usercreated.get("id");

        given()
                .header("Authorization", "Bearer " + APP_TOKEN)
                .when()
                .get("v2/users")
                .then()
                .statusCode(200)
                .body("id", hasItem(idProcurar));


        // ver no gorest se está correto o endPoint

    }
}
