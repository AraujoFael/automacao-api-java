package in.co.gorest.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  class Reutilizaveis {
   public static String retornaDataAtualEmString(){
       LocalDateTime dataAgora = LocalDateTime.now();
       String dataConvertida = String.valueOf(dataAgora.getSecond());
       return dataConvertida;

   }
}
