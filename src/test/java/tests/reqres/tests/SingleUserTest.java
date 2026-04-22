package tests.reqres.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("ReqRes")
@Feature("Default API")
@Story("Single User")
public class SingleUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Get existing user")
    @Description(
            "Get user by id '2'" + "\n" +
                    "Check that user is real")
    @Severity(SeverityLevel.NORMAL)
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
    @DisplayName("Get non-existent user")
    @Description(
            "Get user by id '666'" + "\n" +
                    "Check that status code 404")
    @Severity(SeverityLevel.NORMAL)
    public void getUnrealUserTest() {

        given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/users/666")
                .then()
                .spec(Specifications.responseSpecification(404))
                .log().all();
    }
}
