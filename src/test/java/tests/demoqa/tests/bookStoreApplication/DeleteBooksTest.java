package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void deleteBooksTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();

        Specifications.installSpecifications(Specifications.requestSpecificationDemoQA(URL),
                Specifications.responseSpecification(204));

        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", id)
                .log().all()
                .when()
                .delete("BookStore/v1/Books")
                .then().log().all();

    }
}
