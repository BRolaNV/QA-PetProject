package tests.reqres.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

@Epic("ReqRes")
@Feature("Default API")
@Story("Delete user")
public class DeleteUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Successful delete")
    @Description(
            "Delete user" + "\n" +
                    "Check status code 204")
    @Severity(SeverityLevel.NORMAL)
    public void successDeleteTest() {

        given()
                .spec(requestSpec())
                .when()
                .delete("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(204));

    }
}
