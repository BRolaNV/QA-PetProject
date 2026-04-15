package tests.demoqa.pages.alerts_frame_windows_Page;

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
