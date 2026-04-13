package tests.reqres.tests;
import org.junit.jupiter.api.Test;
import tests.reqres.APIReader;
import tests.reqres.MyProperties;
import tests.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends MyProperties {

    @Test
    public void successDeleteTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(204));

        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();

    }
}
