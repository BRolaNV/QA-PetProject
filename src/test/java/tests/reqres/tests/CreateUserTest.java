package tests.reqres.tests;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ReqRes")
@Feature("Default API")
@Story("Create user")
public class CreateUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Successful create")
    @Description(
            "Create new user" + "\n" +
                    "Check that response id and created time matches ")
    @Severity(SeverityLevel.NORMAL)
    public void successCreateTest() {

        Map<String, String> user = new HashMap<>();
        user.put("name", "morpheus");
        user.put("job", "zeon resident");

        Response response = given()
                .spec(requestSpec())
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .spec(Specifications.responseSpecification(201))
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Instant now = Instant.now();
        Instant serverTime = Instant.parse(jsonPath.get("createdAt"));
        Duration diff = Duration.between(serverTime, now).abs();

        assertTrue(diff.toSeconds() < 10);
        assertNotNull(jsonPath.get("id"));

    }
}
