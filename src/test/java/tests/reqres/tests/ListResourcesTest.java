package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ResourcesData;
import tests.reqres.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ListResourcesTest extends BaseApiTest {

    @Test
    public void dateOrderTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        List<ResourcesData> resources = given()
                .log().all()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourcesData.class);

        List<Integer> years = resources.stream().map(ResourcesData::getYear).toList();
        List<Integer> sorted = years.stream().sorted().toList();

        assertEquals(sorted, years);
    }

    @Test
    public void notNullTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        List<ResourcesData> resources = given()
                .log().all()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourcesData.class);

        resources.forEach(x -> assertNotNull(x.getYear()));
        resources.forEach(x -> assertNotNull(x.getId()));
        resources.forEach(x -> assertNotNull(x.getName()));
        resources.forEach(x -> assertNotNull(x.getColor()));
        resources.forEach(x -> assertNotNull(x.getPantone_value()));
    }
}
