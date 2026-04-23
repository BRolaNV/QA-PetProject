package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ToolTipsPage {

    private final SelenideElement button = $("#toolTipButton"),
            field = $("#toolTipTextField"),
            text = $x("//div[@id='texToolTopContainer']//a[text()='Contrary']"),
            digit = $x("//div[@id='texToolTopContainer']//a[text()='1.10.32']"),
            result = $x("//div[@class='tooltip-inner']");

    @Step("Hover over button")
    public ToolTipsPage hoverButton() {
        actions()
                .moveToLocation(0, 0)
                .moveToElement(button)
                .perform();
        return this;
    }

    @Step("Hover over field")
    public ToolTipsPage hoverField() {
        actions()
                .moveToLocation(0, 0)
                .moveToElement(field)
                .perform();
        return this;
    }

    @Step("Hover over text")
    public ToolTipsPage hoverText() {
        actions()
                .moveToLocation(0, 0)
                .moveToElement(this.text)
                .perform();
        return this;
    }

    @Step("Hover over digit")
    public ToolTipsPage hoverDigit() {
        actions()
                .moveToLocation(0, 0)
                .moveToElement(digit)
                .perform();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
