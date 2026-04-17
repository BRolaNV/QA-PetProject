package tests.demoqa.tests.book_store_application;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.DefaultData;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetUsersBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void getUsersBooksTest(){

        String id = defaultData.getId();
        String token = defaultData.getToken();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("Account/v1/User/"+id)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String responseID = jsonPath.getString("userId");
        String responseUsername =  jsonPath.getString("username");
        List<BookData> books = jsonPath.getList("books");

        assertEquals(defaultData.getValidUser().getUserName(), responseUsername);
        assertEquals(id, responseID);
        assertTrue(books.isEmpty());
    }
}
