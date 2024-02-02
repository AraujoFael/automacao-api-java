package in.co.gorest.core;

import io.restassured.http.ContentType;
public interface Constantes {
    String APP_BASE_URL = "https://gorest.co.in/public/";

   // Integer APP_PORT = ; --> se for uma API local
    String APP_BASE_PATH = "";

    String APP_TOKEN ="17653cf0a61ae53a32558967107d67c2a64f27c62c393a9e6906e18ad732c2ac";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 5000L;
}
