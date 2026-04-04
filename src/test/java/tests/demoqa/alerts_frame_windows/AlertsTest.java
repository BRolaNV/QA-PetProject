package tests.demoqa.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlertsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void alertSimpleTest() {

        open("/alerts");

        $("#alertButton").click();
        assertEquals("You clicked a button", switchTo().alert().getText());
    }

    @Test
    void alertFiveSecondWaitTest() {

        open("/alerts");

        $("#timerAlertButton").click();

        boolean isPresent = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(1))
                .until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

        assertTrue(isPresent);

        Wait().withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        assertEquals("This alert appeared after 5 seconds", switchTo().alert().getText());
    }

    @Test
    void confirmButtonAcceptTest() {

        open("/alerts");

        $("#confirmButton").click();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        switchTo().alert().accept();
        assertEquals("You selected Ok", $("#confirmResult").getText());
    }

    @Test
    void confirmButtonDismissTest() {

        open("/alerts");

        $("#confirmButton").click();
        assertEquals("Do you confirm action?", switchTo().alert().getText());

        $("#confirmButton").click();
        switchTo().alert().dismiss();
        assertEquals("You selected Cancel", $("#confirmResult").getText());
    }

    @Test
    void promptButtonTest() {

        String prompt = new Faker().name().fullName();

        open("/alerts");

        $("#promtButton").click();
        assertEquals("Please enter your name", switchTo().alert().getText());

        switchTo().alert().sendKeys(prompt);
        switchTo().alert().accept();
        assertEquals("You entered " + prompt, $("#promptResult").getText());
    }


    @AfterEach
    void close() {
        closeWebDriver();
    }
}
