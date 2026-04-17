package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.demoqa.tests.bookStoreApplication.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class AuthorizedTest extends BaseApiTest {

    @Test
    public void successAuthorizedTest() {
        UserData validUser = UserData.builder()
                .userName("forAuthorized")
                .password("Pass123@")
                .build();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        given()
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .log().all();

    }

    @Test
    public void notFoundAuthorizedTest() {

        UserData invalidUser = UserData.builder()
                .userName("notFoundUser")
                .password("Pass123@")
                .build();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(404));

        given()
                .log().all()
                .body(invalidUser)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .log().all();

    }
}
