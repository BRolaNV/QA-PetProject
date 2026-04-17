package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.DefaultData;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void changeBookTest(){

        String id = defaultData.getId();
        String token = defaultData.getToken();
        BookData defaultBook = defaultData.getDefaultBook();
        BookData bookForChange = defaultData.getBookForChange();
        defaultData.addDefaultBook();

        Map<String,String> map = new HashMap<>();
        map.put("isbn",bookForChange.getIsbn());
        map.put("userId",id);

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        List<BookData> list = given()
                .header("Authorization", "Bearer " + token)
                .body(map)
                .log().all()
                .when()
                .put("BookStore/v1/Books/"+defaultBook.getIsbn())
                .then().log().all()
                .extract().body().jsonPath().getList("books", BookData.class);

        BookData responseBook = list.get(0);

        assertEquals(bookForChange.getIsbn(), responseBook.getIsbn());
        assertEquals(bookForChange.getAuthor(), responseBook.getAuthor());
        assertEquals(bookForChange.getDescription(), responseBook.getDescription());
        assertEquals(bookForChange.getPublisher(), responseBook.getPublisher());
        assertEquals(bookForChange.getPages(), responseBook.getPages());
        assertEquals(bookForChange.getTitle(), responseBook.getTitle());
        assertEquals(bookForChange.getWebsite(), responseBook.getWebsite());
        assertEquals(bookForChange.getPublish_date(), responseBook.getPublish_date());
        assertEquals(bookForChange.getSubTitle(), responseBook.getSubTitle());

    }
}
