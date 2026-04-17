package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.DefaultData;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void getBookTest(){

        BookData defaultBook = defaultData.getDefaultBook();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        BookData responseBook = given()
                .queryParam("ISBN", defaultBook.getIsbn())
                .when()
                .get("BookStore/v1/Book")
                .then().log().all()
                .extract().body().as(BookData.class);

        assertEquals(defaultBook.getIsbn(), responseBook.getIsbn());
        assertEquals(defaultBook.getAuthor(), responseBook.getAuthor());
        assertEquals(defaultBook.getDescription(), responseBook.getDescription());
        assertEquals(defaultBook.getPublisher(), responseBook.getPublisher());
        assertEquals(defaultBook.getPages(), responseBook.getPages());
        assertEquals(defaultBook.getTitle(), responseBook.getTitle());
        assertEquals(defaultBook.getWebsite(), responseBook.getWebsite());
        assertEquals(defaultBook.getPublish_date(), responseBook.getPublish_date());
        assertEquals(defaultBook.getSubTitle(), responseBook.getSubTitle());


    }
}
