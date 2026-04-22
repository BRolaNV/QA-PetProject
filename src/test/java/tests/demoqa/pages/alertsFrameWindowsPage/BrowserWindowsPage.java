package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BrowserWindowsPage {

    private final SelenideElement tabBtn = $("#tabButton"),
            windowBtn = $("#windowButton"),
            messageWindowBtn = $("#messageWindowButton"),
            result = $("body");

    @Step("Click to open a new tab")
    public BrowserWindowsPage clickTabButton() {
        tabBtn.click();
        return this;
    }

    @Step("Click to open a new window")
    public BrowserWindowsPage clickWindowButton() {
        windowBtn.click();
        return this;
    }

    @Step("Click to open an about:blank")
    public BrowserWindowsPage clickMessageWindowButton() {
        messageWindowBtn.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
