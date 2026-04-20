package tests.demoqa.tests.elements;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.ButtonsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

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
    void doubleClickTest() {
        buttonsPage.doubleClick();
        buttonsPage.getDoubleClickMess().shouldHave(text("You have done a double click"));
    }

    @Test
    void rightClickTest() {
        buttonsPage.rightClick();
        buttonsPage.getRightClickMess().shouldHave(text("You have done a right click"));
    }

    @Test
    void clickMeTest() {
        buttonsPage.clickMe();
        buttonsPage.getClickMeMess().shouldHave(text("You have done a dynamic click"));
    }
}
