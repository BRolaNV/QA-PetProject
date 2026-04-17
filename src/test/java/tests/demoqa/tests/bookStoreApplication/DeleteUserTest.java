package tests.demoqa.tests.bookStoreApplication;

import org.junit.jupiter.api.Test;
import tests.demoqa.tests.bookStoreApplication.pojo.DefaultData;
import tests.demoqa.tests.bookStoreApplication.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseApiTest {

    DefaultData defaultData = new DefaultData();

    @Test
    public void successDeleteUserTest() {

        String id = defaultData.getId();
        String token = defaultData.getToken();

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(204));

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("Account/v1/User/" + id)
                .then().log().all();
    }
}
