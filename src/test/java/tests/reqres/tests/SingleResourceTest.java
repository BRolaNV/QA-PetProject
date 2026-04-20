package tests.reqres.tests;

import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ResourcesData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingleResourceTest extends BaseApiTest {


    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void getRealResourceTest() {

        ResourcesData resource = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/products/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getObject("data", ResourcesData.class);

        ResourcesData realResource = ResourcesData.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantone_value("17-2031")
                .build();

        assertEquals(realResource, resource);
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void getUnrealResourceTest() {

        given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/products/666")
                .then()
                .spec(Specifications.responseSpecification(404))
                .log().all();
    }
}
