package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.TextBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest {

    TextBoxPage textBoxPage =  new TextBoxPage();
    Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage (){
        open("/text-box");
    }

    @Test
    void fillFullFormTest(){

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
    void fillPartialFormTest(){

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
    void fillWrongEmailTest(){

        textBoxPage.setEmail("wrong@email");
        textBoxPage.submit();
        textBoxPage.getOutput().shouldNotBe(visible);
    }

    @Test
    void submitEmptyFormTest(){

        textBoxPage.submit();
        textBoxPage.getOutput().shouldNotBe(visible);
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
