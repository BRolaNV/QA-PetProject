package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AlertsPage {

    private final SelenideElement alertBtn = $("#alertButton"),
            timerAlertBtn = $("#timerAlertButton"),
            confirmBtn = $("#confirmButton"),
            confirmResult = $("#confirmResult"),
            promptBtn = $("#promtButton"),
            promptResult = $("#promptResult");

    @Step("Click the alert button")
    public AlertsPage clickAlertButton() {
        alertBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Click the timer alert button")
    public AlertsPage clickTimerAlertButton() {
        timerAlertBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Click the confirm button")
    public AlertsPage clickConfirmButton() {
        confirmBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Click the prompt button")
    public AlertsPage clickPromptButton() {
        promptBtn.shouldBe(visible).click();
        return this;
    }

    public SelenideElement getConfirmResult() {
        return confirmResult;
    }

    public SelenideElement getPromptResult() {
        return promptResult;
    }

}
