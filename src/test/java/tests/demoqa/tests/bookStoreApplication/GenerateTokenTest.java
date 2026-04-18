package tests.demoqa.tests.bookStoreApplication;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.specifications.Specifications;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GenerateTokenTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    public void successGenerateTokenTest() {

        UserData validUser = defaultData.getValidUser();

        Response response = given()
                .spec(requestSpec())
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String result = jsonPath.get("result");
        String status = jsonPath.get("status");
        String token = jsonPath.get("token");
        String expires = jsonPath.get("expires");

        assertNotNull(token);
        assertNotNull(expires);
        assertEquals("User authorized successfully.", result);
        assertEquals("Success", status);
    }

    //Flaky 406
    @Test
    public void errorGenerateTokenTest() {

        UserData invalidUser = UserData.builder()
                .userName("notFoundUser")
                .password("Pass123@")
                .build();

        Response response = given()
                .spec(requestSpec())
                .log().all()
                .body(invalidUser)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String result = jsonPath.get("result");
        String status = jsonPath.get("status");
        String token = jsonPath.get("token");
        String expires = jsonPath.get("expires");

        assertNull(token);
        assertNull(expires);
        assertEquals("User authorization failed.", result);
        assertEquals("Failed", status);

    }
}
