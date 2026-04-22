package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Get books")
public class GetBooksTest extends BaseApiTest {

    @Test
    @DisplayName("Get all books")
    @Description(
            "Get books" + "\n" +
                    "Check that fields not null")
    @Severity(SeverityLevel.NORMAL)
    public void getBooksTest() {

        List<BookData> books = given()
                .spec(requestSpec())
                .when()
                .get("BookStore/v1/Books")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("books", BookData.class);

        assertEquals(8, books.size());

        books.forEach(book -> {
            assertNotNull(book.getIsbn());
            assertNotNull(book.getTitle());
            assertNotNull(book.getSubTitle());
            assertNotNull(book.getAuthor());
            assertNotNull(book.getPublish_date());
            assertNotNull(book.getPublisher());
            assertNotNull(book.getPages());
            assertNotNull(book.getDescription());
            assertNotNull(book.getWebsite());
        });

    }
}
