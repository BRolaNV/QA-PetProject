package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {

    private final SelenideElement home = $("[aria-label='Select Home']"),
            result = $("#result"),
            react = $("[aria-label='Select React']"),
            switcher = $(".rc-tree-switcher"),
            documentsSwitcher = $x("//span[@aria-label='Select Documents']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]"),
            workSpaceSwitcher = $x("//span[@aria-label='Select WorkSpace']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");

    public CheckBoxPage selectHome() {
        home.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }

    public CheckBoxPage clickSwitcher() {
        switcher.click();
        return this;
    }

    public CheckBoxPage selectReact() {
        react.click();
        return this;
    }

    public CheckBoxPage clickDocumentSwitcher() {
        documentsSwitcher.click();
        return this;
    }

    public CheckBoxPage clickWorkSpaceSwitcher() {
        workSpaceSwitcher.click();
        return this;
    }
}
