package tests.reqres.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.specifications.Specifications;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//Flaky кидает 403 иногда
public class CreateUserTest extends BaseApiTest {

    @Test
    public void successCreateTest() {

        Specifications.installSpecifications(Specifications.requestSpecificationReqRes(URL, API_KEY),
                Specifications.responseSpecification(201));

        Map<String, String> user = new HashMap<>();
        user.put("name", "morpheus");
        user.put("job", "zeon resident");

        Response response = given()
                .body(user)
                .when()
                .post("/api/users")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$", "");

        //Flaky
        assertEquals(currentTime, jsonPath.get("createdAt").toString().replaceAll("(.{5})$", ""));
        assertNotNull(jsonPath.get("id"));

    }
}
