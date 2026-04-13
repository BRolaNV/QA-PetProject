package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.APIReader;
import tests.reqres.MyProperties;
import tests.reqres.POJO.ForUpdateData;
import tests.reqres.specifications.Specifications;

import java.time.Clock;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UpdateUserTest extends MyProperties {

    @Test
    public void fullUpdateTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        ForUpdateData responseUser = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().body().as(ForUpdateData.class);

        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");

        assertEquals(currentTime, responseUser.getUpdatedAt().replaceAll("(.{5})$", ""));
        assertEquals(user.getJob(), responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

    @Test
    public void partialUpdateTest() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .build();

        ForUpdateData responseUser = given()
                .body(user)
                .when()
                .patch("/api/users/2")
                .then().log().all()
                .extract().body().as(ForUpdateData.class);

        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");

        assertEquals(currentTime, responseUser.getUpdatedAt().replaceAll("(.{5})$", ""));
        assertNull(responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

}
