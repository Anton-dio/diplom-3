package site.stellarburgers.generator;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import site.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserApiService {

    private static final String BASE_URL = "https://your-api-url.com";
    private static final String USERS_PATH = "/api/users";

    public static int createUser(User user) {
        ValidatableResponse response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(USERS_PATH)
                .then();

        return response.extract().jsonPath().get("id");
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .delete(USERS_PATH + "/{id}")
                .then();
    }

    private static RequestSpecification getSpec(String bearerPlusToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", bearerPlusToken)
                .contentType("application/json");
    }
}