package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MenuPage {

    private final SelenideElement item1 = $x("//a[text()='Main Item 1']"),
            item2 = $x("//a[text()='Main Item 2']"),
            item3 = $x("//a[text()='Main Item 3']"),
            subItem = $x("//a[text()='Sub Item']"),
            subList = $x("//a[text()='SUB SUB LIST »']"),
            subSubItem1 = $x("//a[text()='Sub Sub Item 1']"),
            subSubItem2 = $x("//a[text()='Sub Sub Item 2']");

    @Step("Hover Item 1")
    public MenuPage hoverItem1() {
        item1.hover();
        return this;
    }

    @Step("Hover Item 2")
    public MenuPage hoverItem2() {
        item2.hover();
        return this;
    }

    @Step("Hover Item 3")
    public MenuPage hoverItem3() {
        item3.hover();
        return this;
    }

    @Step("Hover Subitem")
    public MenuPage hoverSubItem() {
        subList.hover();
        return this;
    }

    public SelenideElement getSubList() {
        return subItem;
    }

    public SelenideElement getSubSubItem1() {
        return subSubItem1;
    }

    public SelenideElement getSubSubItem2() {
        return subSubItem2;
    }
}
