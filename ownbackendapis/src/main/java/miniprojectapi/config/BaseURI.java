package miniprojectapi.config;

import io.restassured.RestAssured;

public class BaseURI {
     public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/api";
    }
}
