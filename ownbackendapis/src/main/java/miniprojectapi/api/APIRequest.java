package miniprojectapi.api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class APIRequest {

    public static Response post(String endpoints, String body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .log().uri()
                .when()
                .post(endpoints);
    }

    public static Response get(String endpoints) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get(endpoints);
    }

    public static Response delete(String endpoints) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .delete(endpoints);
    }

    public static Response put(String endpoints, String body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .when()
                .put(endpoints);
    }
}
