package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class ProgressBarTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void progressBarTest() {

        open("/progress-bar");

        $("#startStopButton").click();
        $("#startStopButton").shouldHave(text("Stop"));
        $("#startStopButton").click();
        $("#startStopButton").shouldHave(text("Start"));
        $("#startStopButton").click();
        $x("//div[@class='progress-bar bg-info']").shouldHave(text("99%"), Duration.ofSeconds(10));
        $("#resetButton").shouldHave(text("Reset"), Duration.ofSeconds(10));
        $x("//div[@class='progress-bar bg-success']").shouldHave(text("100%"));
        $("#resetButton").click();
        $x("//div[@class='progress-bar bg-info']").shouldHave(text("0%"));

    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}

