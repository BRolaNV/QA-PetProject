package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {

    private final SelenideElement home = $("[aria-label='Select Home']"),
            result = $("#result"),
            react = $("[aria-label='Select React']"),
            switcher = $(".rc-tree-switcher"),
            documentsSwitcher = $x("//span[@aria-label='Select Documents']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]"),
            workSpaceSwitcher = $x("//span[@aria-label='Select WorkSpace']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");

    @Step("Select main item")
    public CheckBoxPage selectHome() {
        home.click();
        return this;
    }

    @Step("Open main tree")
    public CheckBoxPage clickSwitcher() {
        switcher.click();
        return this;
    }

    @Step("Select 'React' item")
    public CheckBoxPage selectReact() {
        react.click();
        return this;
    }

    @Step("Open 'document' tree")
    public CheckBoxPage clickDocumentSwitcher() {
        documentsSwitcher.click();
        return this;
    }

    @Step("Open 'Work space' tree")
    public CheckBoxPage clickWorkSpaceSwitcher() {
        workSpaceSwitcher.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
