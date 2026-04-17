package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.ProgressBarPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ProgressBarTest extends BaseUITest {

    ProgressBarPage progressBarPage = new ProgressBarPage();


    @AfterAll
    static void close() {
        closeWebDriver();
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
}

