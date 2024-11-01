package site.stellarburgers.generator;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import site.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserApiService {

    private static final String BASE_URL = "https://your-api-url.com";
    private static final String USERS_PATH = "/api/users";
    private static final String LOGIN_PATH = "/api/auth/login";

    public static int createUser(User user) {
        ValidatableResponse response = given()
                .spec(getSpec(null))
                .body(user)
                .when()
                .post(USERS_PATH)
                .then();

        return response.extract().jsonPath().get("id");
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(int userId, String token) {
        return given()
                .spec(getSpec("Bearer " + token))
                .pathParam("id", userId)
                .when()
                .delete(USERS_PATH + "/{id}")
                .then();
    }

    public static String loginUser(User user) {
        ValidatableResponse response = given()
                .spec(getSpec(null))
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then();

        return response.extract().jsonPath().get("accessToken");
    }

    private static RequestSpecification getSpec(String bearerPlusToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", bearerPlusToken)
                .contentType("application/json");
    }
}