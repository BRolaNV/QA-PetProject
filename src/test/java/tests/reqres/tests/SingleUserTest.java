package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.APIReader;
import tests.reqres.MyProperties;
import tests.reqres.POJO.UserData;
import tests.reqres.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleUserTest extends MyProperties {

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
