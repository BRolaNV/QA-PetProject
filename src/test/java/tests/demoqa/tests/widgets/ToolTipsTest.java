package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ToolTipsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void toolTipsTest(){


        open("/tool-tips");

        $("#toolTipButton").hover();
        $x("//div[@class='tooltip-inner']").shouldHave(text("You hovered over the Button"));

        $("#toolTipTextField").hover();
        $x("//div[@class='tooltip-inner']").shouldHave(text("You hovered over the text field"));

        $x("//div[@id='texToolTopContainer']//a[text()='Contrary']").hover();
        $x("//div[@class='tooltip-inner']").shouldHave(text("You hovered over the Contrary"));

        $x("//div[@id='texToolTopContainer']//a[text()='1.10.32']").hover();
        $x("//div[@class='tooltip-inner']").shouldHave(text("You hovered over the 1.10.32"));

    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
