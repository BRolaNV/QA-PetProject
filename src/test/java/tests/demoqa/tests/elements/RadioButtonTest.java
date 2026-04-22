package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.RadioButtonPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Radio Button")
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
    @DisplayName("Check 'yes' button")
    @Severity(SeverityLevel.NORMAL)
    void tapYesTest() {
        radioButtonPage.clickYesRadio();
        radioButtonPage.getResult().shouldHave(text("yes"));
    }

    @Test
    @DisplayName("Check 'impressive' button")
    @Severity(SeverityLevel.NORMAL)
    void tapImpressiveTest() {
        radioButtonPage.clickImpressiveRadio();
        radioButtonPage.getResult().shouldHave(text("impressive"));
    }

    @Test
    @DisplayName("Check 'No' button")
    @Severity(SeverityLevel.NORMAL)
    void tapNoTest() {
        radioButtonPage.getNoRadio().shouldBe(disabled);
    }
}
