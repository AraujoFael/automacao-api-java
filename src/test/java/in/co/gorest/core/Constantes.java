package in.co.gorest.core;

import io.restassured.http.ContentType;
public interface Constantes {
    String APP_BASE_URL = "https://gorest.co.in/public/";

   // Integer APP_PORT = ; --> se for uma API local
    String APP_BASE_PATH = "";

    String APP_TOKEN ="13731c369fb2a101dbd0b54e576e477f4a0645c2d809543a75d7cc3f757f6aac";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 5000L;
}
