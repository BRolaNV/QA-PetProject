package tests.demoqa.tests.alerts_frame_windows;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BrowserWindowsTest {


    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void newTapButtonTest() {
        open("/browser-windows");

        $("#tabButton").click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    void newWindowButtonTest() {
        open("/browser-windows");

        $("#windowButton").click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    void newWindowMassageButtonTest() {

        Configuration.pageLoadStrategy = "none";
        open("/browser-windows");

        $("#messageWindowButton").click();
        switchTo().window(1);

        $("body").shouldHave(text("Knowledge increases by sharing but not by saving. " +
                "Please share this website with your friends and in your organization."));

        Configuration.pageLoadStrategy = "normal";
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
