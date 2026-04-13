package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.ConfigReader;
import tests.reqres.POJO.UserData;
import tests.reqres.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleUserTest {

    private final static String URL = "https://reqres.in/";
    private final static String API_KEY = ConfigReader.getApiKey();

    @Test
    public void getRealUserTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        UserData user = given()
                .log().all()
                .when()
                .get("/api/users/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);

        UserData realUser = UserData.builder()
                .id(2)
                .email("janet.weaver@reqres.in")
                .first_name("Janet")
                .last_name("Weaver")
                .avatar("https://reqres.in/img/faces/2-image.jpg")
                .build();

        assertTrue(realUser.equals(user));
    }

    @Test
    public void getUnrealUserTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(404));

        UserData user = given()
                .log().all()
                .when()
                .get("/api/users/666")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }
}
