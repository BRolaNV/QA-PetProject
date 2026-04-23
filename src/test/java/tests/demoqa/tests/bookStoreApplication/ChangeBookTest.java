package tests.demoqa.tests.bookStoreApplication;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
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

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Change Book")
public class ChangeBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    @DisplayName("Replace book in user's collection")
    @Description(
            "Create new user" + "\n" +
                    "Login" + "\n" +
                    "Set default book" + "\n" +
                    "Change book" + "\n" +
                    "Check that book is changed" + "\n" +
                    "Delete user")
    @Severity(SeverityLevel.NORMAL)
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
    void cleanUp() {
        defaultData.cleanUp();
    }
}
