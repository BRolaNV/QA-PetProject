package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.widgetsPage.ToolTipsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

/** Flaky не всегда .hover() срабатывал корректно,
 * добавление actions.moveToLocation(0,0) - не помогло, поэтому retry */

public class ToolTipsTest extends BaseUITest {

    ToolTipsPage toolTipsPage = new ToolTipsPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/tool-tips");
    }

    @RetryingTest(3)
    void toolTipsTest() {

        toolTipsPage.hoverButton()
                .getResult()
                .shouldHave(text("You hovered over the Button"));

        toolTipsPage.hoverField()
                .getResult()
                .shouldHave(text("You hovered over the text field"));

        toolTipsPage.hoverText()
                .getResult()
                .shouldHave(text("You hovered over the Contrary"));

        toolTipsPage.hoverDigit()
                .getResult()
                .shouldHave(text("You hovered over the 1.10.32"));

    }
}
