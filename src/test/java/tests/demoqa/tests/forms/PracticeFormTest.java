package tests.demoqa.tests.forms;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.data.PracticeFormData;
import tests.demoqa.pages.formsPage.PracticeFormPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Forms")
@Story("Practice Form")
public class PracticeFormTest extends BaseUITest {

    Faker faker = new Faker();
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/automation-practice-form");
    }

    @Step("Fill required fields")
    public PracticeFormData fillRequiredFields(String gender, String number) {

        PracticeFormData practiceFormData = PracticeFormData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .gender(gender != null ? gender : "Male")
                .number(number != null ? number : faker.number().digits(10))
                .build();

        practiceFormPage.setFirstName(practiceFormData.getFirstName())
                .setLastName(practiceFormData.getLastName())
                .setGender(practiceFormData.getGender())
                .setMobileNumber(practiceFormData.getNumber());

        return practiceFormData;
    }

    @Test
    @DisplayName("Filling out the form correctly")
    @Severity(SeverityLevel.NORMAL)
    void correctFillTest() {

        String email = faker.internet().emailAddress();
        String subject = "English";
        String birthday = "01 January,2001";
        String currentAddress = faker.address().fullAddress();
        String fileName = "./src/test/resources/test.jpg";
        String state = "NCR";
        String city = "Delhi";

        PracticeFormData practiceFormData = fillRequiredFields(null, null);

        practiceFormPage.setUserEmail(email)
                .setSubject(subject)
                .setBirthday(birthday)
                .selectHobbySport()
                .selectHobbyMusic()
                .selectHobbyReading()
                .uploadFile(fileName)
                .selectCurrentAddress(currentAddress)
                .selectState(state)
                .selectCity(city);

        practiceFormPage.submit();

        practiceFormPage.getTable()
                .shouldHave(text(practiceFormData.getFirstName()))
                .shouldHave(text(practiceFormData.getLastName()))
                .shouldHave(text(email))
                .shouldHave(text(practiceFormData.getGender()))
                .shouldHave(text(subject))
                .shouldHave(text(practiceFormData.getNumber()))
                .shouldHave(text(birthday))
                .shouldHave(text(currentAddress))
                .shouldHave(text(state))
                .shouldHave(text(city));
    }

    @Test
    @DisplayName("Change subjects")
    @Severity(SeverityLevel.NORMAL)
    void changeSubjectsTest() {

        String subject1 = "English";
        String subject2 = "Maths";
        String subject3 = "Commerce";

        PracticeFormData practiceFormData = fillRequiredFields("Female", null);
        practiceFormPage.setSubject(subject1).setSubject(subject2).setSubject(subject3);
        practiceFormPage.removeSubject(subject2);
        practiceFormPage.submit();

        practiceFormPage.getTable()
                .shouldHave(text(subject1))
                .shouldNotHave(text(subject2))
                .shouldHave(text(subject3));
    }

    @Test
    @DisplayName("Fill wrong email")
    @Severity(SeverityLevel.NORMAL)
    void wrongEmailTest() {

        String email = "wrong@email";
        PracticeFormData practiceFormData = fillRequiredFields("Other", null);
        practiceFormPage.setUserEmail(email);
        practiceFormPage.submit();
        practiceFormPage.getTable().shouldNotBe(visible);
    }

    @Test
    @DisplayName("Fill wrong mobile number")
    @Severity(SeverityLevel.NORMAL)
    void wrongMobileNumberTest() {

        PracticeFormData practiceFormData = fillRequiredFields(null, faker.number().digits(9));
        practiceFormPage.submit();
        practiceFormPage.getTable().shouldNotBe(visible);
    }
}
