package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    public void deleteBookTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();
        BookData defaultBook = DefaultData.defaultBook;
        defaultData.addDefaultBook();

        Map<String, String> map = new HashMap<>();
        map.put("isbn", defaultBook.getIsbn());
        map.put("userId", id);

        given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .body(map)
                .log().all()
                .when()
                .delete("BookStore/v1/Book")
                .then()
                .spec(Specifications.responseSpecification(204));

    }
}
