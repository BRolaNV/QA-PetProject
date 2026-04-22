package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RadioButtonPage {

    private final SelenideElement result = $x("//span[@class='text-success']"),
            yesRadio = $("#yesRadio"),
            impressiveRadio = $("#impressiveRadio"),
            noRadio = $("#noRadio");

    @Step("Click 'yes' button")
    public RadioButtonPage clickYesRadio() {
        yesRadio.click();
        return this;
    }

    @Step("Click 'impressive' button")
    public RadioButtonPage clickImpressiveRadio() {
        impressiveRadio.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }

    public SelenideElement getNoRadio() {
        return noRadio;
    }
}
