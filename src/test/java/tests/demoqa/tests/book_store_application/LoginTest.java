package tests.demoqa.tests.book_store_application;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class LoginTest {

    Faker faker = new Faker();

    String userName = faker.name().firstName();
    String password = "Pass123@";

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void open(){
        Selenide.open("/login");

        given()
                .contentType("application/json")
                .body("{\"userName\":\""+userName+"\",\"password\":\""+password+"\"}")
                .when()
                .post("https://demoqa.com/Account/v1/User")
                .then()
                .statusCode(201);
    }


    void login(String username, String password){
        $("#userName").setValue(username);
        $("#password").setValue(password);
        $("#login").click();
    }

    @Test
    void invalidLoginTest(){
        String invUserName = "InvalidUserName";
        String invPassword = "InvalidPassword";

        login(invUserName, invPassword);

        $x("//p[@id='name']").shouldHave(text("Invalid username or password!"), Duration.ofSeconds(15));
    }

    @Test
    void successfulLoginTest(){

        login(userName, password);

        $x("//button[text()='Logout']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void logoutTest(){

        login(userName, password);

        $x("//button[text()='Logout']").click();
        $x("//h5[text()='Login in Book Store']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void deleteAccountTest(){

        login(userName, password);
        $x("//button[text()='Logout']").shouldBe(visible, Duration.ofSeconds(15));

        String userId = given()
                .contentType("application/json")
                .body("{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}")
                .post("https://demoqa.com/Account/v1/Login")
                .then().extract().path("userId");

        String token = given()
                .contentType("application/json")
                .body("{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}")
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then().extract().path("token");

        given()
                .header("Authorization", "Bearer " + token)
                .delete("https://demoqa.com/Account/v1/User/" + userId)
                .then().statusCode(204);

        closeWebDriver();
        Selenide.open("/login");

        login(userName, password);
        $x("//p[@id='name']").shouldHave(text("Invalid username or password!"), Duration.ofSeconds(15));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
