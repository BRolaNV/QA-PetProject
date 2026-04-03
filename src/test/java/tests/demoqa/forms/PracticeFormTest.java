package tests.demoqa.forms;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTest {

    Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void happyTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String genderValue = "Male";
        String subject = "English";
        String mobileNumber = faker.number().digits(10);
        String birthday = "01 January,2001";
        String currentAddress = faker.address().fullAddress();
        String state = "NCR";
        String city = "Delhi";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//input[@type='radio'][@value='"+genderValue+"']").click();
        $x("//input[@placeholder='Mobile Number']").setValue(mobileNumber);
        $x("//div[@class='react-datepicker__input-container']").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("0");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("2001");
        $x("//div[contains(@class,'react-datepicker__day')][text()='1'][1]").click();
        $("#subjectsInput").setValue(subject);
        $x("//div[contains(@class,'subjects-auto-complete__option')]").click();
        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-2").click();
        $("#hobbies-checkbox-3").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/test.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state);
        $x("//div[text()='"+state+"']").click();
        $("#react-select-4-input").setValue(city);
        $x("//div[text()='"+city+"']").click();

        $x("//button[text()='Submit']").click(ClickOptions.usingJavaScript());

        $("tbody").shouldHave(text(firstName));
        $("tbody").shouldHave(text(lastName));
        $("tbody").shouldHave(text(email));
        $("tbody").shouldHave(text(genderValue));
        $("tbody").shouldHave(text(subject));
        $("tbody").shouldHave(text(mobileNumber));
        $("tbody").shouldHave(text(birthday));
        $("tbody").shouldHave(text(currentAddress));
        $("tbody").shouldHave(text(state));
        $("tbody").shouldHave(text(city));
    }

    @Test
    void changeSubjectsTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String genderValue = "Male";
        String subject1 = "English";
        String subject2 = "Maths";
        String subject3 = "Commerce";
        String mobileNumber = faker.number().digits(10);

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//input[@type='radio'][@value='"+genderValue+"']").click();
        $x("//input[@placeholder='Mobile Number']").setValue(mobileNumber);

        $("#subjectsInput").setValue(subject1);
        $x("//div[contains(@class,'subjects-auto-complete__option')]").click();
        $("#subjectsInput").setValue(subject2);
        $x("//div[contains(@class,'subjects-auto-complete__option')]").click();
        $("#subjectsInput").setValue(subject3);
        $x("//div[contains(@class,'subjects-auto-complete__option')]").click();

        $x("//div[@aria-label='Remove "+subject2+"']").click();

        $x("//button[text()='Submit']").click(ClickOptions.usingJavaScript());

        $("tbody").shouldHave(text(subject1));
        $("tbody").shouldNotHave(text(subject2));
        $("tbody").shouldHave(text(subject3));
    }

    @Test
    void wrongEmailTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "wrong@email";
        String genderValue = "Male";
        String mobileNumber = faker.number().digits(10);

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//input[@type='radio'][@value='"+genderValue+"']").click();
        $x("//input[@placeholder='Mobile Number']").setValue(mobileNumber);

        $x("//button[text()='Submit']").click(ClickOptions.usingJavaScript());

        $("tbody").shouldNotBe(visible);
    }

    @Test
    void wrongMobileNumberTest() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String genderValue = "Male";
        String mobileNumber = faker.number().digits(9);

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//input[@type='radio'][@value='"+genderValue+"']").click();
        $x("//input[@placeholder='Mobile Number']").setValue(mobileNumber);

        $x("//button[text()='Submit']").click(ClickOptions.usingJavaScript());

        $("tbody").shouldNotBe(visible);
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
