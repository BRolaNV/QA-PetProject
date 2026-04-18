package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class AuthorizedTest extends BaseApiTest {

    DefaultData defaultData =  new DefaultData().init();

    @Test
    public void successAuthorizedTest() {

        UserData validUser = defaultData.getValidUser();

        given()
                .spec(requestSpec())
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .spec(Specifications.responseSpecification(200));

    }

    @Test
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
}
