package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {

    private final SelenideElement startStopBtn = $("#startStopButton"),
            resetBtn = $("#resetButton"),
            successResult = $x("//div[@class='progress-bar bg-success']"),
            progressBar = $x("//div[@class='progress-bar bg-info']");

    @Step("Click 'Start/Stop' button")
    public ProgressBarPage clickStartStopBtn() {
        startStopBtn.click();
        return this;
    }

    @Step("Click 'Reset' button")
    public ProgressBarPage clickResetBtn() {
        resetBtn.click();
        return this;
    }

    public SelenideElement getStartStopBtnText() {
        return startStopBtn;
    }

    public SelenideElement getResetBtnText() {
        return resetBtn;
    }

    public SelenideElement getSuccessResult() {
        return successResult;
    }

    public SelenideElement getProgressBarText() {
        progressBar.shouldBe(Condition.visible);
        return progressBar;
    }


}
