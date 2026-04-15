package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.ToolTipsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ToolTipsTest {

    ToolTipsPage toolTipsPage =  new ToolTipsPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/tool-tips");
    }

    @Test
    void toolTipsTest(){

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


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
