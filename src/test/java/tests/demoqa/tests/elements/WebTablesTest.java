package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.data.WebTablesData;
import tests.demoqa.pages.elementsPage.WebTablesPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class WebTablesTest {

    WebTablesPage webTablesPage = new WebTablesPage();
    Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/webtables");
    }

    public WebTablesData fillFormAndSubmit(String email, String age, String salary){

        WebTablesData webTablesData = new WebTablesData(
                faker.name().firstName(),
                faker.name().lastName(),
                email != null ? email : faker.internet().emailAddress(),
                age != null ? age : faker.number().digits(2),
                salary != null ? salary : faker.number().digits(8),
                "Same Department");

        webTablesPage.setFirstName(webTablesData.getFirstName())
                .setLastName(webTablesData.getLastName())
                .setUserEmail(webTablesData.getEmail())
                .setAge(webTablesData.getAge())
                .setSalary(webTablesData.getSalary())
                .setDepartment(webTablesData.getDepartment());

        webTablesPage.clickSubmit();

        return webTablesData;
    }

    @Test
    void successAddTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, null);

        webTablesPage.getTable()
                .shouldHave(text(webTablesData.getFirstName()))
                .shouldHave(text(webTablesData.getLastName()))
                .shouldHave(text(webTablesData.getEmail()));
    }

    @Test
    void wrongEmailTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit("wrong@email", null, null);

        webTablesPage.getTable()
                .shouldNotHave(text(webTablesData.getFirstName()))
                .shouldNotHave(text(webTablesData.getLastName()))
                .shouldNotHave(text(webTablesData.getEmail()));
    }

    @Test
    void negativeAgeTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, String.valueOf(faker.number().negative()), null);

        webTablesPage.getTable()
                .shouldNotHave(text(webTablesData.getFirstName()))
                .shouldNotHave(text(webTablesData.getLastName()))
                .shouldNotHave(text(webTablesData.getEmail()));
    }

    @Test
    void negativeSalaryTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, String.valueOf(faker.number().negative()));

        webTablesPage.getTable()
                .shouldNotHave(text(webTablesData.getFirstName()))
                .shouldNotHave(text(webTablesData.getLastName()))
                .shouldNotHave(text(webTablesData.getEmail()));
    }

    @Test
    void notDigitTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, "age", "salary");

        webTablesPage.getTable()
                .shouldNotHave(text(webTablesData.getFirstName()))
                .shouldNotHave(text(webTablesData.getLastName()))
                .shouldNotHave(text(webTablesData.getEmail()));
    }

    @Test
    void successSearchTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, null);
        webTablesPage.search(webTablesData.getFirstName());

        webTablesPage.getTable()
                .shouldHave(text(webTablesData.getFirstName()))
                .shouldHave(text(webTablesData.getLastName()))
                .shouldHave(text(webTablesData.getEmail()));
    }

    @Test
    void wrongSearchTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, null);
        webTablesPage.search("SameTextForWrongSearch");

        webTablesPage.getTable().shouldNotBe(visible);
    }

    @Test
    void editButtonTest() {

        webTablesPage.clickEditBtn();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, null);

        webTablesPage.getTable()
                .shouldHave(text(webTablesData.getFirstName()))
                .shouldHave(text(webTablesData.getLastName()))
                .shouldHave(text(webTablesData.getEmail()));
    }

    @Test
    void deleteButtonTest() {

        webTablesPage.addNewRecord();
        WebTablesData webTablesData = fillFormAndSubmit(null, null, null);

        webTablesPage.getTable()
                .shouldHave(text(webTablesData.getFirstName()))
                .shouldHave(text(webTablesData.getLastName()))
                .shouldHave(text(webTablesData.getEmail()));

        webTablesPage.delete(webTablesData.getFirstName());

        webTablesPage.getTable()
                .shouldNotHave(text(webTablesData.getFirstName()))
                .shouldNotHave(text(webTablesData.getLastName()))
                .shouldNotHave(text(webTablesData.getEmail()));
    }

    @Test
    void navigationButtonsTest() {

        for (int i = 0; i < 50; i++) {

            webTablesPage.addNewRecord();
            WebTablesData webTablesData = fillFormAndSubmit(null, null, null);
        }

        webTablesPage.getNavigationBtnResult().shouldHave(text("1 of 6"));

        webTablesPage.clickNextBtn();
        webTablesPage.getNavigationBtnResult().shouldHave(text("2 of 6"));

        webTablesPage.clickPreviousBtn();
        webTablesPage.getNavigationBtnResult().shouldHave(text("1 of 6"));

        webTablesPage.clickLastBtn();
        webTablesPage.getNavigationBtnResult().shouldHave(text("6 of 6"));

        webTablesPage.clickFirstBtn();
        webTablesPage.getNavigationBtnResult().shouldHave(text("1 of 6"));

        webTablesPage.select("Show 50");
        webTablesPage.getNavigationBtnResult().shouldHave(text("1 of 2"));
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
