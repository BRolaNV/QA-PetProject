package tests.demoqa.tests.alertsFrameWindows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alertsFrameWindowsPage.BrowserWindowsPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BrowserWindowsTest extends BaseUITest {

    BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "none";
        Configuration.timeout = 20000;
    }

    @BeforeEach
    void openPage() {
        open("/browser-windows");
    }

    @Test
    void newTapButtonTest() {

        browserWindowsPage.clickTabButton();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    void newWindowButtonTest() {

        browserWindowsPage.clickWindowButton();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    void newWindowMassageButtonTest() {

        browserWindowsPage.clickMessageWindowButton();

        switchTo().window(1);

        browserWindowsPage.getResult().shouldHave(text("Knowledge increases by sharing but not by saving. " +
                "Please share this website with your friends and in your organization."), Duration.ofSeconds(10));

    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
