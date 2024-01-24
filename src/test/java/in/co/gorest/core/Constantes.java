package in.co.gorest.core;

import io.restassured.http.ContentType;
public interface Constantes {
    String APP_BASE_URL = "https://gorest.co.in/public/";

   // Integer APP_PORT = ; --> se for uma API local
    String APP_BASE_PATH = "";

    String APP_TOKEN ="a363762856f44341a3ca97bbd6f07152eaaa9885f8ea7949c008d34c06d09731";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 5000L;
}
