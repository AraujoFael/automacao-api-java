package in.co.gorest.core;

import io.restassured.http.ContentType;
public interface Constantes {
  String APP_BASE_URL = "https://gorest.co.in/public/";

   // Integer APP_PORT = ; --> se for uma API local
    String APP_BASE_PATH = "";

    String APP_TOKEN ="dbceb252568fb795c309197e8e05a994ced253a2ec42b4e6a06db0c57e917b5a";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 5000L;
}
