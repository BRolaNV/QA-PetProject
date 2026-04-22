package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Get book")
public class GetBookTest extends BaseApiTest {

    @Test
    @DisplayName("Get single book")
    @Description(
            "Select default book" + "\n" +
                    "Send book" + "\n" +
                    "Check that response matches default book")
    @Severity(SeverityLevel.NORMAL)
    public void getBookTest() {

        BookData defaultBook = DefaultData.defaultBook;

        BookData responseBook = given()
                .spec(requestSpec())
                .queryParam("ISBN", defaultBook.getIsbn())
                .when()
                .get("BookStore/v1/Book")
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().as(BookData.class);

        assertEquals(defaultBook, responseBook);

    }
}
