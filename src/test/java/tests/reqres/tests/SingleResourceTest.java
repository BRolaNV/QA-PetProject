package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.ConfigReader;
import tests.reqres.POJO.ResourcesData;
import tests.reqres.POJO.UserData;
import tests.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleResourceTest {

    private final static String URL = "https://reqres.in/";
    private final static String API_KEY = ConfigReader.getApiKey();

    @Test
    public void getRealResourceTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        ResourcesData resource = given()
                .log().all()
                .when()
                .get("/api/products/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ResourcesData.class);

        ResourcesData realResource = ResourcesData.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantone_value("17-2031")
                .build();

        assertTrue(realResource.equals(resource));
    }

    @Test
    public void getUnrealResourceTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(404));

        ResourcesData resourcesData = given()
                .log().all()
                .when()
                .get("/api/products/666")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ResourcesData.class);
    }
}
