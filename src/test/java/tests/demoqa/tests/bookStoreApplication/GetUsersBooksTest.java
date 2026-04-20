package tests.demoqa.tests.bookStoreApplication;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.BookData;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUsersBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    public void getUsersBooksTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();

        Response response = given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get("Account/v1/User/" + id)
                .then()
                .spec(Specifications.responseSpecification(200))
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String responseID = jsonPath.getString("userId");
        String responseUsername = jsonPath.getString("username");
        List<BookData> books = jsonPath.getList("books");

        assertEquals(defaultData.getValidUser().getUserName(), responseUsername);
        assertEquals(id, responseID);
        assertTrue(books.isEmpty());
    }

    @AfterEach
    void cleanUp(){
        defaultData.cleanUp();
    }
}
