package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DelayTest extends BaseApiTest {

    @Test
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
