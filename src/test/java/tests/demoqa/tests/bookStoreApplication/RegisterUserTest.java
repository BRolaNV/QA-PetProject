package tests.demoqa.tests.bookStoreApplication;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserTest extends BaseApiTest {

    @Test
    public void successRegisterTest() {

        UserData validUser = UserData.builder()
                .userName(new Faker().name().firstName() + "_" + System.currentTimeMillis())
                .password("Pass123@")
                .build();

        String responseUsername = given()
                .spec(requestSpec())
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/User")
                .then()
                .spec(Specifications.responseSpecification(201))
                .log().all()
                .extract().jsonPath().getString("username");

        assertEquals(validUser.getUserName(), responseUsername);
    }

    @Test
    public void unsuccessfulRegisterTest() {

        UserData invalidUser = UserData.builder()
                .userName(new Faker().name().firstName())
                .password("Password")
                .build();

        String errorMess = given()
                .spec(requestSpec())
                .log().all()
                .body(invalidUser)
                .when()
                .post("Account/v1/User")
                .then()
                .spec(Specifications.responseSpecification(400))
                .log().all()
                .extract().jsonPath().getString("message");

        assertEquals("Passwords must have at least one non alphanumeric character, " +
                "one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), " +
                "one special character and Password must be eight characters or longer.", errorMess);

    }

}
