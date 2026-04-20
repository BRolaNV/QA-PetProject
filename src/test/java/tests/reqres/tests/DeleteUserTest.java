package tests.reqres.tests;

import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void successDeleteTest() {

        given()
                .spec(requestSpec())
                .when()
                .delete("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(204));

    }
}
