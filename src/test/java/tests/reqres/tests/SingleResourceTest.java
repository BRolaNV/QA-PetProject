package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ResourcesData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//Flaky иногда кидает 403
public class SingleResourceTest extends BaseApiTest {


    @Test
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

    @Test
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
