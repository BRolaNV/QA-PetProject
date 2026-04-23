package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ButtonsPage {

    private final SelenideElement doubleClickBtn = $("#doubleClickBtn"),
            rightClickBtn = $("#rightClickBtn"),
            clickMeBtn = $x("//button[text()='Click Me']"),
            doubleClickMessage = $("#doubleClickMessage"),
            rightClickMessage = $("#rightClickMessage"),
            dynamicClickMessage = $("#dynamicClickMessage");

    @Step("Double click")
    public ButtonsPage doubleClick() {
        doubleClickBtn.shouldBe(visible);
        doubleClickBtn.doubleClick();
        return this;
    }

    @Step("Right click")
    public ButtonsPage rightClick() {
        rightClickBtn.shouldBe(visible);
        rightClickBtn.contextClick();
        return this;
    }

    @Step("Simple click")
    public ButtonsPage clickMe() {
        clickMeBtn.shouldBe(visible);
        clickMeBtn.click();
        return this;
    }

    public SelenideElement getDoubleClickMess() {
        return doubleClickMessage;
    }

    public SelenideElement getRightClickMess() {
        return rightClickMessage;
    }

    public SelenideElement getClickMeMess() {
        return dynamicClickMessage;
    }

}
