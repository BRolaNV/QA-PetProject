package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.widgetsPage.ProgressBarPage;
import tests.demoqa.tests.BaseUITest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Progress Bar")
@Flaky
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

    @RetryingTest(3)
    @DisplayName("Check progress bar filling and control buttons")
    @Severity(SeverityLevel.NORMAL)
    void progressBarTest() {

        progressBarPage.clickStartStopBtn()
                .getStartStopBtnText()
                .shouldHave(text("Stop"));

        progressBarPage.clickStartStopBtn()
                .getStartStopBtnText()
                .shouldHave(text("Start"));

        progressBarPage.clickStartStopBtn();

        progressBarPage.getResetBtnText().shouldHave(text("Reset"), Duration.ofSeconds(15));
        progressBarPage.getSuccessResult().shouldHave(text("100%"));

        progressBarPage.clickResetBtn()
                .getProgressBarText()
                .shouldHave(text("0%"));

    }
}

