package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TabsPage {

    private final SelenideElement useBtn = $("#demo-tab-use"),
            originBtn = $("#demo-tab-origin"),
            whatBtn = $("#demo-tab-what"),
            result = $x("//div[@class='fade tab-pane active show']//p");

    @Step("Open 'Use' page")
    public TabsPage openUse() {
        useBtn.click();
        return this;
    }

    @Step("Open 'Origin' page")
    public TabsPage openOrigin() {
        originBtn.click();
        return this;
    }

    @Step("Open 'What' page")
    public TabsPage openWhat() {
        whatBtn.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
