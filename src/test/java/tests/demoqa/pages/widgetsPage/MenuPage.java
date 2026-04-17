package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MenuPage {

    private final SelenideElement item1 = $x("//a[text()='Main Item 1']"),
            item2 = $x("//a[text()='Main Item 2']"),
            item3 = $x("//a[text()='Main Item 3']"),
            subItem = $x("//a[text()='Sub Item']"),
            subList = $x("//a[text()='SUB SUB LIST »']"),
            subSubItem1 = $x("//a[text()='Sub Sub Item 1']"),
            subSubItem2 = $x("//a[text()='Sub Sub Item 2']");

    public MenuPage hoverItem1() {
        item1.hover();
        return this;
    }

    public MenuPage hoverItem2() {
        item2.hover();
        return this;
    }

    public MenuPage hoverItem3() {
        item3.hover();
        return this;
    }

    public MenuPage hoverSubList() {
        subList.hover();
        return this;
    }

    public SelenideElement getSubList() {
        return subItem;
    }

    public SelenideElement getSubSubList1() {
        return subSubItem1;
    }

    public SelenideElement getSubSubList2() {
        return subSubItem2;
    }
}
