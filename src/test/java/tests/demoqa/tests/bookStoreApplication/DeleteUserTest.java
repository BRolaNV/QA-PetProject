package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.baseTest.BaseApiTest;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData().init();

    @Test
    public void successDeleteUserTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();

        given()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("Account/v1/User/" + id)
                .then()
                .spec(Specifications.responseSpecification(204));
    }
}
