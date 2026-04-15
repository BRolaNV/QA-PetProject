package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.ProgressBarPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProgressBarTest {

    ProgressBarPage progressBarPage = new ProgressBarPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/progress-bar");
    }

    @Test
    void progressBarTest() {

        progressBarPage.clickStartStopBtn()
                .getStartStopBtnText()
                .shouldHave(text("Stop"));

        progressBarPage.clickStartStopBtn()
                .getStartStopBtnText()
                .shouldHave(text("Start"));

        progressBarPage.clickStartStopBtn()
                .getProgressBarText()
                .shouldHave(text("99%"), Duration.ofSeconds(10));

        progressBarPage.getResetBtnText().shouldHave(text("Reset"), Duration.ofSeconds(10));
        progressBarPage.getSuccessResult().shouldHave(text("100%"));

        progressBarPage.clickResetBtn()
                .getProgressBarText()
                .shouldHave(text("0%"));

    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}

