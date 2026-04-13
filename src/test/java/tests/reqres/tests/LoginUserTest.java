package tests.reqres.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.reqres.APIReader;
import tests.reqres.MyProperties;
import tests.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginUserTest extends MyProperties {


    @Test
    public void successLoginTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        Response response = given()
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("/api/login")
                .then().log().all()
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

    }

    @Test
    public void unsuccessLoginTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(400));

        Response response = given()
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/login")
                .then().log().all()
                .body("error",  equalTo("Missing password"))
                .extract().response();
    }
}
