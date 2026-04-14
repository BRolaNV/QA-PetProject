package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.ButtonsPage;

import java.awt.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ButtonsTest {

    ButtonsPage buttonsPage = new ButtonsPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
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

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
