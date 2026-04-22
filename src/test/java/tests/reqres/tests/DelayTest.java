package tests.reqres.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("ReqRes")
@Feature("Default API")
@Story("Delayed response")
public class DelayTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Get users with delayed response")
    @Description(
            "Send request with 3 sec delay" + "\n" +
                    "Check that response data is valid")
    @Severity(SeverityLevel.NORMAL)
    public void getDelayUsers() {

        List<UserData> users = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("api/users?delay=3")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> assertNotNull(x.getId()));
        users.forEach(x -> assertNotNull(x.getAvatar()));

    }
}
