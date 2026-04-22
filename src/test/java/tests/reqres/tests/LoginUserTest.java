package tests.reqres.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("ReqRes")
@Feature("Default API")
@Story("Login user")
public class LoginUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Successful login")
    @Description(
            "Login valid user" + "\n" +
                    "Check token")
    @Severity(SeverityLevel.NORMAL)
    public void successLoginTest() {

        given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("/api/login")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Unsuccessful login")
    @Description(
            "Login user without password" + "\n" +
                    "Check error 'Missing password'")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulLoginTest() {

        given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/login")
                .then()
                .spec(Specifications.responseSpecification(400))
                .log().all()
                .body("error", equalTo("Missing password"));
    }
}
