package tests.reqres.tests;
import org.junit.jupiter.api.Test;
import tests.ConfigReader;
import tests.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    private final static String URL = "https://reqres.in/";
    private final static String API_KEY = ConfigReader.getApiKey();

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
