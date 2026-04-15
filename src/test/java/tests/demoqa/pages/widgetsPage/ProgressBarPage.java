package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {

    private final SelenideElement startStopBtn = $("#startStopButton"),
            resetBtn = $("#resetButton"),
            successResult = $x("//div[@class='progress-bar bg-success']"),
            progressBar = $x("//div[@class='progress-bar bg-info']");

    public SelenideElement getStartStopBtnText() {
        return startStopBtn;
    }

    public SelenideElement getResetBtnText() {
        return resetBtn;
    }

    public ProgressBarPage clickStartStopBtn() {
        startStopBtn.click();
        return this;
    }

    public ProgressBarPage clickResetBtn() {
        resetBtn.click();
        return this;
    }

    public SelenideElement getSuccessResult() {
        return successResult;
    }

    public SelenideElement getProgressBarText() {
        return progressBar;
    }


}
