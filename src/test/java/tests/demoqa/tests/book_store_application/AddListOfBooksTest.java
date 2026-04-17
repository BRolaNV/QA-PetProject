package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.DefaultData;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddListOfBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void addListOfBooksTest(){

        String id = defaultData.getId();
        String token = defaultData.getToken();
        BookData defaultBook = defaultData.getDefaultBook();

        Map<String, Object> body = Map.of(
                "userId", id,
                "collectionOfIsbns", List.of(Map.of("isbn", defaultBook.getIsbn()))
        );

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(201));

        String isbn = given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("BookStore/v1/Books")
                .then().log().all()
                .extract().body().jsonPath().getString("books[0].isbn");

        assertEquals(defaultBook.getIsbn(), isbn);

    }
}
