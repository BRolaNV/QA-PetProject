package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Authorized")
public class AuthorizedTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    @DisplayName("Successful authorized")
    @Description(
            "Create new user" + "\n" +
                    "Authorized" + "\n" +
                    "Check that result is true" + "\n" +
                    "Delete user")
    @Severity(SeverityLevel.NORMAL)
    public void successAuthorizedTest() {

        UserData validUser = defaultData.getValidUser();

        Boolean result = given()
                .spec(requestSpec())
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .spec(Specifications.responseSpecification(200))
                .extract().body().as(Boolean.class);

        assertTrue(result);

    }

    @Test
    @DisplayName("Unsuccessful Authorized")
    @Description(
            "Create new invalid user" + "\n" +
                    "Authorized" + "\n" +
                    "Check that response has 404")
    @Severity(SeverityLevel.NORMAL)
    public void notFoundAuthorizedTest() {

        UserData invalidUser = UserData.builder()
                .userName("notFoundUser")
                .password("Pass123@")
                .build();

        given()
                .spec(requestSpec())
                .log().all()
                .body(invalidUser)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .spec(Specifications.responseSpecification(404));

    }

    @AfterEach
    void cleanUp() {
        defaultData.cleanUp();
    }
}
