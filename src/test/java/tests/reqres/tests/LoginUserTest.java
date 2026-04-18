package tests.reqres.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginUserTest extends BaseApiTest {
    //flaky отдельно работает, совместно выдает 403
    @Test
    public void successLoginTest() {

        Response response = given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("/api/login")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

    }

    //flaky отдельно работает, совместно выдает 403
    @Test
    public void unsuccessfulLoginTest() {

        Response response = given()
                .spec(requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/login")
                .then()
                .spec(Specifications.responseSpecification(400))
                .log().all()
                .body("error", equalTo("Missing password"))
                .extract().response();
    }
}
