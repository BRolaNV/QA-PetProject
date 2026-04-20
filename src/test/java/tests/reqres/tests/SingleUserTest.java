package tests.reqres.tests;

import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingleUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void getRealUserTest() {

        UserData user = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);

        UserData realUser = UserData.builder()
                .id(2)
                .email("janet.weaver@reqres.in")
                .first_name("Janet")
                .last_name("Weaver")
                .avatar("https://reqres.in/img/faces/2-image.jpg")
                .build();

        assertEquals(realUser, user);
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void getUnrealUserTest() {

        UserData user = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/users/666")
                .then()
                .spec(Specifications.responseSpecification(404))
                .log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }
}
