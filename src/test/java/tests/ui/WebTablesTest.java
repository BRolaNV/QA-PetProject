package tests.ui;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTablesTest {

    Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successAddTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldHave(text(firstName));
        $("tbody").shouldHave(text(lastName));
        $("tbody").shouldHave(text(email));
    }

    @Test
    void wrongEmailTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "wrong@email";
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldNotHave(text(firstName));
        $("tbody").shouldNotHave(text(lastName));
        $("tbody").shouldNotHave(text(email));
    }

    @Test
    void negativeAgeTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = String.valueOf(faker.number().negative());
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldNotHave(text(firstName));
        $("tbody").shouldNotHave(text(lastName));
        $("tbody").shouldNotHave(text(email));
    }

    @Test
    void negativeSalaryTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = String.valueOf(faker.number().negative());
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldNotHave(text(firstName));
        $("tbody").shouldNotHave(text(lastName));
        $("tbody").shouldNotHave(text(email));
    }

    @Test
    void notDigitTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = "age";
        String salary = "salary";
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldNotHave(text(firstName));
        $("tbody").shouldNotHave(text(lastName));
        $("tbody").shouldNotHave(text(email));
    }

    @Test
    void successSearchTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("#searchBox").setValue(firstName);

        $("tbody").shouldHave(text(firstName));
        $("tbody").shouldHave(text(lastName));
        $("tbody").shouldHave(text(email));
    }

    @Test
    void wrongSearchTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("#searchBox").setValue("SameTextForWrongSearch");

        $("tbody").shouldNotBe(visible);
    }

    @Test
    void editButtonTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#edit-record-1").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldHave(text(firstName));
        $("tbody").shouldHave(text(lastName));
        $("tbody").shouldHave(text(email));
    }

    @Test
    void deleteButtonTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = faker.number().digits(2);
        String salary = faker.number().digits(8);
        String department = "Same Department";

        open("/webtables");

        $("#addNewRecordButton").click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);

        $("#submit").click();

        $("tbody").shouldHave(text(firstName));
        $("tbody").shouldHave(text(lastName));
        $("tbody").shouldHave(text(email));

        $x("//td[text()='" + firstName + "']/following-sibling::td//span[@title='Delete']").click();

        $("tbody").shouldNotHave(text(firstName));
        $("tbody").shouldNotHave(text(lastName));
        $("tbody").shouldNotHave(text(email));
    }

    @Test
    void navigationButtonsTest() {

        open("/webtables");

        for (int i = 0; i < 50; i++) {

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();
            String age = faker.number().digits(2);
            String salary = faker.number().digits(8);
            String department = "Same Department";

            $("#addNewRecordButton").click();

            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#age").setValue(age);
            $("#salary").setValue(salary);
            $("#department").setValue(department);

            $("#submit").click();
        }

        $("strong").shouldHave(text("1 of 6"));

        $x("//button[text()='Next']").click(ClickOptions.usingJavaScript());
        $("strong").shouldHave(text("2 of 6"));

        $x("//button[text()='Previous']").click(ClickOptions.usingJavaScript());
        $("strong").shouldHave(text("1 of 6"));

        $x("//button[text()='Last']").click(ClickOptions.usingJavaScript());
        $("strong").shouldHave(text("6 of 6"));

        $x("//button[text()='First']").click(ClickOptions.usingJavaScript());
        $("strong").shouldHave(text("1 of 6"));

        $("select").selectOption("Show 50");
        $("strong").shouldHave(text("1 of 2"));


    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
