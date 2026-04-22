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
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic("DemoQA API")
@Feature("Book Store")
@Story("Delete Book")
public class DeleteBookTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    @DisplayName("Delete single books")
    @Description(
            "Create new user" + "\n" +
                    "Login" + "\n" +
                    "Add default book" + "\n" +
                    "Delete default book" + "\n" +
                    "Check that status code 204" + "\n" +
                    "Delete user")
    @Severity(SeverityLevel.NORMAL)
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

    @AfterEach
    void cleanUp(){
        defaultData.cleanUp();
    }
}
