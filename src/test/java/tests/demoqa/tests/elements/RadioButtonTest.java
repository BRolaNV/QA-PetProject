package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.RadioButtonPage;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTest {

    RadioButtonPage radioButtonPage = new RadioButtonPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/radio-button");
    }

    @Test
    void tapYesTest() {
        radioButtonPage.clickYesRadio();
        radioButtonPage.getResult().shouldHave(text("yes"));
    }

    @Test
    void tapImpressiveTest() {
        radioButtonPage.clickImpressiveRadio();
        radioButtonPage.getResult().shouldHave(text("impressive"));
    }

    @Test
    void tapNoTest() {
        radioButtonPage.getNoRadio().shouldBe(disabled);
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
