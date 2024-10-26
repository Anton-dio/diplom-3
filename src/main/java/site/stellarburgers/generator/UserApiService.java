package site.stellarburgers.generator;

import io.restassured.response.Response;
import site.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserApiService {

    public static int createUser(User user) {
        Response response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();

        return response.jsonPath().get("id");
    }

    public static void deleteUser(int userId) {
        given()
                .pathParam("id", userId)
                .when()
                .delete("/api/users/{id}")
                .then()
                .statusCode(200);
    }
}