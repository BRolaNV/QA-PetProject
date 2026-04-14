package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ButtonsPage {

    private final SelenideElement doubleClickBtn = $("#doubleClickBtn"),
            rightClickBtn = $("#rightClickBtn"),
            clickMeBtn = $x("//button[text()='Click Me']"),
            doubleClickMessage = $("#doubleClickMessage"),
            rightClickMessage = $("#rightClickMessage"),
            dynamicClickMessage = $("#dynamicClickMessage");

    public ButtonsPage doubleClick() {
        doubleClickBtn.doubleClick();
        return this;
    }

    public ButtonsPage rightClick() {
        rightClickBtn.contextClick();
        return this;
    }

    public ButtonsPage clickMe() {
        clickMeBtn.click();
        return this;
    }

    public SelenideElement getDoubleClickMess() { return doubleClickMessage; }
    public SelenideElement getRightClickMess() { return rightClickMessage; }
    public SelenideElement getClickMeMess() { return dynamicClickMessage; }

}
