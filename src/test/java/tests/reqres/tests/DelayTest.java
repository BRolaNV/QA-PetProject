package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.reqres.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DelayTest extends BaseApiTest {

    @Test
    public void getDelayUsers() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        List<UserData> users = given()
                .log().all()
                .when()
                .get("api/users?delay=3")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> assertNotNull(x.getId()));
        users.forEach(x -> assertNotNull(x.getAvatar()));

    }
}
