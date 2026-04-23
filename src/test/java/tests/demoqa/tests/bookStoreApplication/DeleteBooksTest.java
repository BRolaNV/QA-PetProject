package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Delete Books")
public class DeleteBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    @DisplayName("Delete all books")
    @Description(
            "Create new user" + "\n" +
                    "Login" + "\n" +
                    "Delete books" + "\n" +
                    "Check that status code 204" + "\n" +
                    "Delete user")
    @Severity(SeverityLevel.NORMAL)
    public void deleteBooksTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();

        given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", id)
                .log().all()
                .when()
                .delete("BookStore/v1/Books")
                .then()
                .spec(Specifications.responseSpecification(204));

    }

    @AfterEach
    void cleanUp() {
        defaultData.cleanUp();
    }
}
