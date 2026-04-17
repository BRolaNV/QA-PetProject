package tests.demoqa.tests.bookStoreApplication;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.pojo.UserData;
import tests.demoqa.tests.bookStoreApplication.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserTest extends BaseApiTest {

    @Test
    public void successRegisterTest() {

        UserData validUser = UserData.builder()
                .userName(new Faker().name().firstName())
                .password("Pass123@")
                .build();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(201));

        String responseUsername = given()
                .log().all()
                .body(validUser)
                .when()
                .post("Account/v1/User")
                .then()
                .log().all()
                .extract().jsonPath().getString("username");

        assertEquals(validUser.getUserName(), responseUsername);
    }

    @Test
    public void unsuccessRegisterTest() {

        UserData invalidUser = UserData.builder()
                .userName(new Faker().name().firstName())
                .password("Password")
                .build();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(400));

        String errorMess = given()
                .log().all()
                .body(invalidUser)
                .when()
                .post("Account/v1/User")
                .then()
                .log().all()
                .extract().jsonPath().getString("message");

        assertEquals("Passwords must have at least one non alphanumeric character, " +
                "one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), " +
                "one special character and Password must be eight characters or longer.", errorMess);

    }

}
