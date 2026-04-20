package tests.reqres.tests;

import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ResourcesData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ListResourcesTest extends BaseApiTest {

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void dateOrderTest() {

        List<ResourcesData> resources = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/unknown")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("data", ResourcesData.class);

        List<Integer> years = resources.stream().map(ResourcesData::getYear).toList();
        List<Integer> sorted = years.stream().sorted().toList();

        assertEquals(sorted, years);
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    public void notNullTest() {

        List<ResourcesData> resources = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("/api/unknown")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("data", ResourcesData.class);

        resources.forEach(x -> assertNotNull(x.getYear()));
        resources.forEach(x -> assertNotNull(x.getId()));
        resources.forEach(x -> assertNotNull(x.getName()));
        resources.forEach(x -> assertNotNull(x.getColor()));
        resources.forEach(x -> assertNotNull(x.getPantone_value()));
    }
}
