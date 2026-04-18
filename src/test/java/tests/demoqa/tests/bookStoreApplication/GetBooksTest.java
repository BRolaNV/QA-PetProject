package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetBooksTest extends BaseApiTest {

    @Test
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
