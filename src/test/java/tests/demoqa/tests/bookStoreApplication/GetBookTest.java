package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBookTest extends BaseApiTest {

    @Test
    public void getBookTest() {

        BookData defaultBook = DefaultData.defaultBook;

        Specifications.installSpecifications(Specifications.requestSpecificationDemoQA(URL),
                Specifications.responseSpecification(200));

        BookData responseBook = given()
                .queryParam("ISBN", defaultBook.getIsbn())
                .when()
                .get("BookStore/v1/Book")
                .then().log().all()
                .extract().body().as(BookData.class);

        assertEquals(defaultBook, responseBook);

    }
}
