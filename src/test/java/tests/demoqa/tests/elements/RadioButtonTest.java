package tests.demoqa.tests.elements;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.RadioButtonPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class RadioButtonTest extends BaseUITest {

    RadioButtonPage radioButtonPage = new RadioButtonPage();


    @AfterAll
    static void close() {
        closeWebDriver();
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
}
