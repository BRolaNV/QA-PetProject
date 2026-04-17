package tests.demoqa.tests.book_store_application;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.book_store_application.specifications.BaseApiTest;
import tests.demoqa.tests.book_store_application.specifications.DefaultData;
import tests.demoqa.tests.book_store_application.specifications.Specifications;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteBooksTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void deleteBooksTest(){

        String id = defaultData.getId();
        String token = defaultData.getToken();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
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
