package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Add books")
public class AddListOfBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    @DisplayName("Add list of books")
    @Description(
            "Create new user" + "\n" +
            "Login" + "\n" +
            "Add list of books" + "\n" +
            "Check that book is added" + "\n" +
            "Delete user")
    @Severity(SeverityLevel.NORMAL)
    public void addListOfBooksTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();
        BookData defaultBook = DefaultData.defaultBook;

        Map<String, Object> body = Map.of(
                "userId", id,
                "collectionOfIsbns", List.of(Map.of("isbn", defaultBook.getIsbn()))
        );

        String isbn = given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(Specifications.responseSpecification(201))
                .log().all()
                .extract().body().jsonPath().getString("books[0].isbn");

        assertEquals(defaultBook.getIsbn(), isbn);

    }

    @AfterEach
    void cleanUp(){
        defaultData.cleanUp();
    }
}
