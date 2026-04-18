package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

//Flaky иногда кидает 403
public class DeleteUserTest extends BaseApiTest {

    @Test
    public void successDeleteTest() {

        Specifications.installSpecifications(Specifications.requestSpecificationReqRes(URL, API_KEY),
                Specifications.responseSpecification(204));

        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();

    }
}
