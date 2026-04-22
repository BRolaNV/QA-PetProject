package tests.demoqa.tests.alertsFrameWindows;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import tests.demoqa.pages.alertsFrameWindowsPage.BrowserWindowsPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Epic("DemoQA UI")
@Feature("Alerts, Frame & Windows")
@Story("Browser Windows")
public class BrowserWindowsTest extends BaseUITest {

    BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage();

    @BeforeAll
    static void setUpPageLoad() {
        Configuration.pageLoadStrategy = "none";
        Configuration.timeout = 20000;
    }

    @BeforeEach
    void openPage() {
        open("/browser-windows");
    }

    @Test
    @DisplayName("Open new tab")
    @Severity(SeverityLevel.NORMAL)
    void newTabButtonTest() {

        browserWindowsPage.clickTabButton();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    @DisplayName("Open new window")
    @Severity(SeverityLevel.NORMAL)
    void newWindowButtonTest() {

        browserWindowsPage.clickWindowButton();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/sample"));
    }

    @Test
    @DisplayName("Open new window (about:blank)")
    @Severity(SeverityLevel.NORMAL)
    void newWindowMessageButtonTest() {

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
