package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AlertsPage {

    private final SelenideElement alertBtn = $("#alertButton"),
            timerAlertBtn = $("#timerAlertButton"),
            confirmBtn = $("#confirmButton"),
            confirmResult = $("#confirmResult"),
            promptBtn = $("#promtButton"),
            promptResult = $("#promptResult");

    public AlertsPage clickAlertButton() {
        alertBtn.click();
        return this;
    }

    public AlertsPage clickTimerAlertButton() {
        timerAlertBtn.click();
        return this;
    }

    public AlertsPage clickConfirmButton() {
        confirmBtn.click();
        return this;
    }

    public AlertsPage clickPromptButton() {
        promptBtn.click();
        return this;
    }

    public SelenideElement getConfirmResult() {
        return confirmResult;
    }

    public SelenideElement getPromptResult() {
        return promptResult;
    }

}
