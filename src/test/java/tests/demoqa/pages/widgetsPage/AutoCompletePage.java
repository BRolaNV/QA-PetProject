package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AutoCompletePage {

    private final SelenideElement inputField = $("#autoCompleteMultipleInput"),
            singleInputField = $("#autoCompleteSingleInput"),
            chooseBtn = $x("//div[contains(@class,'auto-complete__option')]"),
            result = $x("//div[contains(@class,'auto-complete__value-container')][1]"),
            singleValue = $x("//div[contains(@class,'auto-complete__single-value')]");

    public AutoCompletePage remove(String value) {
        $x("//div[@aria-label='Remove " + value + "']").click();
        return this;
    }

    public AutoCompletePage multipleInput(String value) {
        inputField.setValue(value);
        chooseBtn.click();
        return this;
    }

    public AutoCompletePage singleInput(String value) {
        singleInputField.setValue(value);
        chooseBtn.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }

    public SelenideElement getSingleValue() {
        return singleValue;
    }
}
