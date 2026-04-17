package tests.demoqa.tests.book_store_application.specifications;

import lombok.Getter;
import net.datafaker.Faker;
import tests.demoqa.tests.book_store_application.POJO.BookData;
import tests.demoqa.tests.book_store_application.POJO.UserData;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Getter
public class DefaultData extends BaseApiTest{

    UserData validUser = new UserData(new Faker().name().firstName(), "Pass123@");

    String id = registerUser();
    String token = loginUser();

    BookData defaultBook = BookData.builder()
            .isbn("9781449325862")
            .title("Git Pocket Guide")
            .subTitle("A Working Introduction")
            .author("Richard E. Silverman")
            .publish_date("2020-06-04T08:48:39.000Z")
            .publisher("O'Reilly Media")
            .pages(234)
            .description("This pocket guide is the perfect on-the-job companion to Git, " +
                    "the distributed version control system. It provides a compact, " +
                    "readable introduction to Git for new users, " +
                    "as well as a reference to common commands and procedures for those of you with Git exp")
            .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html")
            .build();

    BookData bookForChange = BookData.builder()
            .isbn("9781449331818")
            .title("Learning JavaScript Design Patterns")
            .subTitle("A JavaScript and jQuery Developer's Guide")
            .author("Addy Osmani")
            .publish_date("2020-06-04T09:11:40.000Z")
            .publisher("O'Reilly Media")
            .pages(254)
            .description("With Learning JavaScript Design Patterns, " +
                    "you'll learn how to write beautiful, structured, " +
                    "and maintainable JavaScript by applying classical and modern design patterns to the language. " +
                    "If you want to keep your code efficient, more manageable, and up-to-da")
            .website("http://www.addyosmani.com/resources/essentialjsdesignpatterns/book/")
            .build();

    String registerUser(){

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(201));

        return given()
                .body(validUser)
                .when()
                .post("Account/v1/User")
                .then()
                .extract().jsonPath().getString("userID");
    }

    String loginUser(){

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        return given()
                .body(validUser)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .extract().jsonPath().getString("token");
    }

    public void addDefaultBook(){

        Map<String, Object> body = Map.of(
                "userId", id,
                "collectionOfIsbns", List.of(Map.of("isbn", defaultBook.getIsbn()))
        );

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(201));

        given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("BookStore/v1/Books")
                .then().log().all();

    }
}
