package tests.reqres.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ForUpdateData;
import tests.specifications.Specifications;

import java.time.Duration;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("ReqRes")
@Feature("Default API")
@Story("Update User")
public class UpdateUserTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Full update")
    @Description(
            "Create user with new data" + "\n" +
                    "Send" + "\n" +
                    "Check that data is updated")
    @Severity(SeverityLevel.NORMAL)
    public void fullUpdateTest() {

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        ForUpdateData responseUser = given()
                .spec(requestSpec())
                .body(user)
                .when()
                .put("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().as(ForUpdateData.class);

        Instant now = Instant.now();
        Instant serverTime = Instant.parse(responseUser.getUpdatedAt());
        Duration diff = Duration.between(serverTime, now).abs();

        assertTrue(diff.toSeconds() < 10);
        assertEquals(user.getJob(), responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Partial update")
    @Description(
            "Create user with new name only" + "\n" +
                    "Send" + "\n" +
                    "Check that only name is updated")
    @Severity(SeverityLevel.NORMAL)
    public void partialUpdateTest() {

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .build();

        ForUpdateData responseUser = given()
                .spec(requestSpec())
                .body(user)
                .when()
                .patch("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().as(ForUpdateData.class);

        Instant now = Instant.now();
        Instant serverTime = Instant.parse(responseUser.getUpdatedAt());
        Duration diff = Duration.between(serverTime, now).abs();

        assertTrue(diff.toSeconds() < 10);
        assertNull(responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

}
