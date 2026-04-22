package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.ButtonsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Buttons")
public class ButtonsTest extends BaseUITest {

    ButtonsPage buttonsPage = new ButtonsPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/buttons");
    }

    @Test
    @DisplayName("Verify text after double click")
    @Severity(SeverityLevel.NORMAL)
    void doubleClickTest() {
        buttonsPage.doubleClick();
        buttonsPage.getDoubleClickMess().shouldHave(text("You have done a double click"));
    }

    @Test
    @DisplayName("Verify text after right click")
    @Severity(SeverityLevel.NORMAL)
    void rightClickTest() {
        buttonsPage.rightClick();
        buttonsPage.getRightClickMess().shouldHave(text("You have done a right click"));
    }

    @Test
    @DisplayName("Verify text after simple click")
    @Severity(SeverityLevel.NORMAL)
    void clickMeTest() {
        buttonsPage.clickMe();
        buttonsPage.getClickMeMess().shouldHave(text("You have done a dynamic click"));
    }
}
