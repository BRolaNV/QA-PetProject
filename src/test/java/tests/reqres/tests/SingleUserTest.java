package tests.reqres.tests;

import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//Flaky иногда кидает 403
public class SingleUserTest extends BaseApiTest {

    @Test
    public void getRealUserTest() {

        Specifications.installSpecifications(Specifications.requestSpecificationReqRes(URL, API_KEY),
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

        assertEquals(realUser, user);
    }

    //flaky отдельно отрабатывает, при совместном вызове выдает 403
    @Test
    public void getUnrealUserTest() {

        Specifications.installSpecifications(Specifications.requestSpecificationReqRes(URL, API_KEY),
                Specifications.responseSpecification(404));

        UserData user = given()
                .log().all()
                .when()
                .get("/api/users/666")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }
}
