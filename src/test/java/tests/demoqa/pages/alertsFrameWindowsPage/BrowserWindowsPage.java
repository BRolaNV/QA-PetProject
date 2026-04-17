package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BrowserWindowsPage {

    private final SelenideElement tabBtn = $("#tabButton"),
            windowBtn = $("#windowButton"),
            messageWindowBtn = $("#messageWindowButton"),
            result = $("body");

    public BrowserWindowsPage clickTabButton() {
        tabBtn.click();
        return this;
    }

    public BrowserWindowsPage clickWindowButton() {
        windowBtn.click();
        return this;
    }

    public BrowserWindowsPage clickMessageWindowButton() {
        messageWindowBtn.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
