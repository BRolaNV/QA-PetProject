package tests.reqres.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class RegisterUserTest extends BaseApiTest {

    //flaky отдельно отрабатывает, если вызывать вместе выдает 403
    @Test
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

    @Test
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
