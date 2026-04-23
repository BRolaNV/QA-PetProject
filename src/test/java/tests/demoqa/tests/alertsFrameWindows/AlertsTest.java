package tests.demoqa.tests.alertsFrameWindows;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.demoqa.pages.alertsFrameWindowsPage.AlertsPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("DemoQA UI")
@Feature("Alerts, Frame & Windows")
@Story("Alerts")
public class AlertsTest extends BaseUITest {

    AlertsPage alertsPage = new AlertsPage();

    @BeforeEach
    void openPage() {
        open("/alerts");
    }

    @Test
    @DisplayName("Click Button to see alert")
    @Severity(SeverityLevel.NORMAL)
    void alertSimpleTest() {

        alertsPage.clickAlertButton();
        assertEquals("You clicked a button", switchTo().alert().getText());
    }

    @Test
    @DisplayName("On button click, alert will appear after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void alertFiveSecondWaitTest() {

        alertsPage.clickTimerAlertButton();

        boolean isNotPresent = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(1))
                .until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

        assertTrue(isNotPresent);

        Wait().withTimeout(Duration.ofSeconds(7)).until(ExpectedConditions.alertIsPresent());
        assertEquals("This alert appeared after 5 seconds", switchTo().alert().getText());
    }

    @Test
    @DisplayName("On button click, confirm box will appear. Set accept")
    @Severity(SeverityLevel.NORMAL)
    void confirmButtonAcceptTest() {

        alertsPage.clickConfirmButton();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        switchTo().alert().accept();
        assertEquals("You selected Ok", alertsPage.getConfirmResult().getText());
    }

    @Test
    @DisplayName("On button click, confirm box will appear. Set dismiss")
    @Severity(SeverityLevel.MINOR)
    void confirmButtonDismissTest() {

        alertsPage.clickConfirmButton();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        switchTo().alert().dismiss();
        assertEquals("You selected Cancel", alertsPage.getConfirmResult().getText());
    }

    @Test
    @DisplayName("On button click, prompt box will appear")
    @Severity(SeverityLevel.NORMAL)
    void promptButtonTest() {

        String prompt = new Faker().name().fullName();

        alertsPage.clickPromptButton();
        assertEquals("Please enter your name", switchTo().alert().getText());

        switchTo().alert().sendKeys(prompt);
        switchTo().alert().accept();
        assertEquals("You entered " + prompt, alertsPage.getPromptResult().getText());
    }


    @AfterEach
    void close() {
        closeWebDriver();
    }
}
