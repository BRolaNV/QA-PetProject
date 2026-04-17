package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.UserData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizedTest extends BaseApiTest {

    @Test
    public void successAuthorizedTest(){
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
    public void notFoundAuthorizedTest(){

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
