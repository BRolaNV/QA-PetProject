package tests.reqres.tests;

import io.restassured.response.Response;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class RegisterUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void successRegisterTest() {

        Response response = given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("/api/register")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void unsuccessfulRegisterTest() {

        Response response = given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/register")
                .then()
                .spec(Specifications.responseSpecification(400))
                .log().all()
                .body("error", equalTo("Missing password"))
                .extract().response();
    }

}
