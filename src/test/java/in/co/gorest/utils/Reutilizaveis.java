package in.co.gorest.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static in.co.gorest.core.Constantes.APP_TOKEN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public  class Reutilizaveis {
   public static String retornaDataAtualEmString(){
       LocalDateTime dataAgora = LocalDateTime.now();
       String dataConvertida = String.valueOf(dataAgora.getSecond());
       return dataConvertida;

   }
    public static Map createUser(String namePrefix) {
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
}
