package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetBooksTest extends BaseApiTest {

    @Test
    public void getBooksTest(){

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        List<BookData> books = given()
                .when()
                .get("BookStore/v1/Books")
                .then().log().all()
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
