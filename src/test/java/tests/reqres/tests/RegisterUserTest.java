package tests.reqres.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("ReqRes")
@Feature("Default API")
@Story("Register user")
public class RegisterUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Successful register")
    @Description(
            "Register new user" + "\n" +
                    "Check token and id")
    @Severity(SeverityLevel.NORMAL)
    public void successRegisterTest() {

        given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("/api/register")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Unsuccessful register")
    @Description(
            "Register new user without password" + "\n" +
                    "Check error 'Missing password'")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulRegisterTest() {

        given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/register")
                .then()
                .spec(Specifications.responseSpecification(400))
                .log().all()
                .body("error", equalTo("Missing password"));
    }

}
