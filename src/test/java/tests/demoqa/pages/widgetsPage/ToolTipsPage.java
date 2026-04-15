package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ToolTipsPage {

    private final SelenideElement button = $("#toolTipButton"),
            field = $("#toolTipTextField"),
            text = $x("//div[@id='texToolTopContainer']//a[text()='Contrary']"),
            digit = $x("//div[@id='texToolTopContainer']//a[text()='1.10.32']"),
            result = $x("//div[@class='tooltip-inner']");

    public SelenideElement getResult() {
        return result;
    }

    public ToolTipsPage hoverButton() {
        button.hover();
        return this;
    }

    public ToolTipsPage hoverField() {
        field.hover();
        return this;
    }

    public ToolTipsPage hoverText() {
        text.hover();
        return this;
    }

    public ToolTipsPage hoverDigit() {
        digit.hover();
        return this;
    }
}
