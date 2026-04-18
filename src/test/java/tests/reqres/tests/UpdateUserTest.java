package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.ForUpdateData;
import tests.specifications.Specifications;

import java.time.Clock;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//Flaky иногда кидает 403
public class UpdateUserTest extends BaseApiTest {

    @Test
    public void fullUpdateTest() {

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        ForUpdateData responseUser = given()
                .spec(requestSpec())
                .body(user)
                .when()
                .put("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().as(ForUpdateData.class);

        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");

        //Flaky
        assertEquals(currentTime, responseUser.getUpdatedAt().replaceAll("(.{5})$", ""));
        assertEquals(user.getJob(), responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

    @Test
    public void partialUpdateTest() {

        ForUpdateData user = ForUpdateData.builder()
                .name("morpheus")
                .build();

        ForUpdateData responseUser = given()
                .spec(requestSpec())
                .body(user)
                .when()
                .patch("/api/users/2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().as(ForUpdateData.class);

        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");
//Flaky
        assertEquals(currentTime, responseUser.getUpdatedAt().replaceAll("(.{5})$", ""));
        assertNull(responseUser.getJob());
        assertEquals(user.getName(), responseUser.getName());

    }

}
