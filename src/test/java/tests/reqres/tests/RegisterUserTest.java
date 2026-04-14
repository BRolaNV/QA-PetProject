package tests.reqres.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.specifications.Specifications;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class RegisterUserTest extends BaseApiTest {


    @Test
    public void successRegisterTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        Response response = given()
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("/api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

    }

    @Test
    public void unsuccessRegisterTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(400));

        Response response = given()
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/api/register")
                .then().log().all()
                .body("error",  equalTo("Missing password"))
                .extract().response();
    }

}
