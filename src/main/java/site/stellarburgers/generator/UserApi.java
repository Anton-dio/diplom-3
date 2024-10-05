package site.stellarburgers.generator;

import io.restassured.RestAssured;
import org.asynchttpclient.Response;
import site.stellarburgers.model.User;

public class UserApi {

    private static final String BASE_URL = "https://example.com/api"; // Замените на ваш URL

    public static int createUser(User user) {
        Response response = (Response) RestAssured.given()
                .contentType("application/json")
                .body(user)
                .post(BASE_URL + "/users");

        return response.hashCode();
    }

    public static void deleteUser(String email) {
        RestAssured.delete(BASE_URL + "/users/" + email);
    }
}