package tests.demoqa.tests.alertsFrameWindows;

import com.codeborne.selenide.WebDriverRunner;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.demoqa.pages.alertsFrameWindowsPage.AlertsPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertsTest extends BaseUITest {

    AlertsPage alertsPage = new AlertsPage();

    @BeforeEach
    void openPage() {
        open("/alerts");
    }

    @Test
    void alertSimpleTest() {

        alertsPage.clickAlertButton();
        assertEquals("You clicked a button", switchTo().alert().getText());
    }

    @Test
    void alertFiveSecondWaitTest() {

        alertsPage.clickTimerAlertButton();

        boolean isNotPresent = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(1))
                .until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

        assertTrue(isNotPresent);

        Wait().withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        assertEquals("This alert appeared after 5 seconds", switchTo().alert().getText());
    }

    @Test
    void confirmButtonAcceptTest() {

        alertsPage.clickConfirmButton();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        switchTo().alert().accept();
        assertEquals("You selected Ok", alertsPage.getConfirmResult().getText());
    }

    @Test
    void confirmButtonDismissTest() {

        alertsPage.clickConfirmButton();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        switchTo().alert().dismiss();
        assertEquals("You selected Cancel", alertsPage.getConfirmResult().getText());
    }

    @Test
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
