package tests.reqres.tests;


import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.reqres.BaseApiTest;
import tests.reqres.pojo.RootData;
import tests.reqres.pojo.UserData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ReqRes")
@Feature("Default API")
@Story("List users")
public class ListUsersTest extends BaseApiTest {

    @Step("Get users")
    public List<UserData> getUsers() {

        List<UserData> users = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("api/users?page=2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        return users;
    }

    @Step("Get root")
    public RootData getRoot() {

        RootData rootData = given()
                .spec(requestSpec())
                .log().all()
                .when()
                .get("api/users?page=2")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getObject(".", RootData.class);

        return rootData;
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Check avatar and id")
    @Description(
            "Get users" + "\n" +
                    "Check that avatar and id are equal")
    @Severity(SeverityLevel.NORMAL)
    public void checkAvatarAndIdTest() {

        List<UserData> users = getUsers();
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Check emails")
    @Description(
            "Get users" + "\n" +
                    "Check that emails end with 'reqres.in'")
    @Severity(SeverityLevel.NORMAL)
    public void checkEmailsTest() {

        List<UserData> users = getUsers();
        users.forEach(x -> assertTrue(x.getEmail().endsWith("reqres.in")));
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Check number of elements")
    @Description(
            "Get users" + "\n" +
                    "Get root" + "\n" +
                    "Check that the quantity matches")
    @Severity(SeverityLevel.NORMAL)
    public void checkNumberOfElementsTest() {

        RootData rootData = getRoot();
        List<UserData> users = rootData.getData();

        assertEquals(rootData.getPer_page(), users.size());
    }

    @RetryingTest(maxAttempts = 3, suspendForMs = 2000)
    @DisplayName("Check root")
    @Description(
            "Get root" + "\n" +
                    "Check that the quantity matches")
    @Severity(SeverityLevel.NORMAL)
    public void checkRootTest() {

        RootData rootData = getRoot();
        assertEquals(2, rootData.getPage());
        assertEquals(12, rootData.getTotal());
    }
}
