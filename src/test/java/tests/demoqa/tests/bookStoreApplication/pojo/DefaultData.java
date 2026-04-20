package tests.demoqa.tests.bookStoreApplication.pojo;

import lombok.Getter;
import net.datafaker.Faker;
import tests.specifications.Specifications;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Getter
public class DefaultData {

    public static BookData defaultBook = BookData.builder()
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
    public static BookData bookForChange = BookData.builder()
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

    private final String URL = "https://demoqa.com/";

    private final UserData validUser = new UserData(
            new Faker().name().firstName() + "_" + System.currentTimeMillis(),
                    "Pass123@"
    );

    private String id;
    private String token;

    public DefaultData init() {
        this.id = registerUser();
        this.token = loginUser();
        return this;
    }

    public void setToken(String token){
        this.token = token;
    }

    private String registerUser() {

        return given()
                .spec(Specifications.requestSpecificationDemoQA(URL))
                .body(validUser)
                .when()
                .post("Account/v1/User")
                .then()
                .spec(Specifications.responseSpecification(201))
                .extract().jsonPath().getString("userID");
    }

    private String loginUser() {

        return given()
                .spec(Specifications.requestSpecificationDemoQA(URL))
                .body(validUser)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .spec(Specifications.responseSpecification(200))
                .extract().jsonPath().getString("token");
    }

    public void addDefaultBook() {
        if (id == null || token == null) {
            throw new IllegalStateException("DefaultData not initialized. Call init() first.");
        }

        Map<String, Object> body = Map.of(
                "userId", id,
                "collectionOfIsbns", List.of(Map.of("isbn", defaultBook.getIsbn()))
        );

        given()
                .spec(Specifications.requestSpecificationDemoQA(URL))
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(Specifications.responseSpecification(201))
                .log().all();

    }

    public void cleanUp() {

        if (id == null || token == null) {
            return;
        }

        given()
                .spec(Specifications.requestSpecificationDemoQA(URL))
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("Account/v1/User/" + id)
                .then()
                .spec(Specifications.responseSpecification(204));
    }
}
