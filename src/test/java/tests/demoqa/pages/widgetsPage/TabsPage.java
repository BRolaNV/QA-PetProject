package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TabsPage {

    private final SelenideElement useBtn = $("#demo-tab-use"),
            originBtn = $("#demo-tab-origin"),
            whatBtn = $("#demo-tab-what"),
            result = $x("//div[@class='fade tab-pane active show']//p");

    public SelenideElement getResult() {
        return result;
    }

    public TabsPage openUse() {
        useBtn.click();
        return this;
    }

    public TabsPage openOrigin() {
        originBtn.click();
        return this;
    }

    public TabsPage openWhat() {
        whatBtn.click();
        return this;
    }
}
