package site.stellarburgers.generator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import site.stellarburgers.model.User;

public class UserApi {

    private static final String BASE_URL = "https://example.com/api"; // Замените на ваш URL

    public static User createUser(User user) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(user)
                .post(BASE_URL + "/users");

        return response.as(User.class);
    }

    public static void deleteUser(String email) {
        RestAssured.delete(BASE_URL + "/users/" + email);
    }
}