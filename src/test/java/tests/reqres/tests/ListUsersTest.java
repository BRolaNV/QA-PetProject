package tests.reqres.tests;


import org.junit.jupiter.api.Test;
import tests.reqres.BaseApiTest;
import tests.reqres.POJO.RootData;
import tests.reqres.POJO.UserData;
import tests.reqres.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListUsersTest extends BaseApiTest {


    public List<UserData> getUsers() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        List<UserData> users = given()
                .log().all()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        return users;
    }

    public RootData getRoot() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL, API_KEY),
                Specifications.responseSpecification(200));

        RootData rootData = given()
                .log().all()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getObject(".", RootData.class);

        return rootData;
    }

    @Test
    public void checkAvatarAndIdTest(){

        List<UserData> users = getUsers();
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test
    public void checkEmailsTest(){

        List<UserData> users = getUsers();
        users.forEach(x -> assertTrue(x.getEmail().endsWith("reqres.in")));
    }

    @Test
    public void checkNumberOfElementsTest(){

        RootData rootData = getRoot();
        List<UserData> users = rootData.getData();

        assertEquals(rootData.getPer_page(), users.size());
    }

    @Test
    public void checkRootTest(){

        RootData rootData = getRoot();
        assertEquals(2, rootData.getPage());
        assertEquals(12, rootData.getTotal());
    }
}
