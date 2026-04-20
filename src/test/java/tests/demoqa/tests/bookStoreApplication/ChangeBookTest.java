package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    public void changeBookTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();
        BookData defaultBook = DefaultData.defaultBook;
        BookData bookForChange = DefaultData.bookForChange;
        defaultData.addDefaultBook();

        Map<String, String> map = new HashMap<>();
        map.put("isbn", bookForChange.getIsbn());
        map.put("userId", id);

        List<BookData> list = given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .body(map)
                .log().all()
                .when()
                .put("BookStore/v1/Books/" + defaultBook.getIsbn())
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().body().jsonPath().getList("books", BookData.class);

        BookData responseBook = list.get(0);

        assertEquals(bookForChange, responseBook);
    }

    @AfterEach
    void cleanUp(){
        defaultData.cleanUp();
    }
}
