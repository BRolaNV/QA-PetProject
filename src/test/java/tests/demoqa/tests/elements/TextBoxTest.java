package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.TextBoxPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Text Box")
public class TextBoxTest extends BaseUITest {

    TextBoxPage textBoxPage = new TextBoxPage();
    Faker faker = new Faker();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/text-box");
    }

    @Test
    @DisplayName("Filling out the form correctly")
    @Severity(SeverityLevel.NORMAL)
    void fillFullFormTest() {

        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String currentAddress = faker.address().fullAddress();
        String permanentAddress = faker.address().fullAddress();

        textBoxPage.setName(name)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress);

        textBoxPage.submit();

        textBoxPage.getOutputName().shouldHave(text(name));
        textBoxPage.getOutputEmail().shouldHave(text(email));
        textBoxPage.getOutputCurrentAddress().shouldHave(text(currentAddress));
        textBoxPage.getOutputPermanentAddress().shouldHave(text(permanentAddress));
    }

    @Test
    @DisplayName("Fill only required fields")
    @Severity(SeverityLevel.NORMAL)
    void fillPartialFormTest() {

        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String currentAddress = faker.address().fullAddress();

        textBoxPage.setName(name)
                .setEmail(email)
                .setCurrentAddress(currentAddress);

        textBoxPage.submit();

        textBoxPage.getOutputName().shouldHave(text(name));
        textBoxPage.getOutputEmail().shouldHave(text(email));
        textBoxPage.getOutputCurrentAddress().shouldHave(text(currentAddress));
        textBoxPage.getOutputPermanentAddress().shouldNotBe(visible);
    }

    @Test
    @DisplayName("Fill wrong email")
    @Severity(SeverityLevel.NORMAL)
    void fillWrongEmailTest() {

        textBoxPage.setEmail("wrong@email");
        textBoxPage.submit();
        textBoxPage.getOutput().shouldNotBe(visible);
    }

    @Test
    @DisplayName("Try submit empty form")
    @Severity(SeverityLevel.NORMAL)
    void submitEmptyFormTest() {

        textBoxPage.submit();
        textBoxPage.getOutput().shouldNotBe(visible);
    }
}
