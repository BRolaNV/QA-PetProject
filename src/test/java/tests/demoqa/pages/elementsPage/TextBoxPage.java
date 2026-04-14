package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

    private final SelenideElement nameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputName = $("#output #name"),
            outputEmail = $("#output #email"),
            outputCurrentAddress = $("#output #currentAddress"),
            outputPermanentAddress = $("#output #permanentAddress"),
            output = $("#output");

    public TextBoxPage setName(String name) {
        nameInput.setValue(name);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public TextBoxPage setPermanentAddress(String address) {
        permanentAddressInput.setValue(address);
        return this;
    }

    public void submit() {
        submitButton.click(ClickOptions.usingJavaScript());
    }

    public SelenideElement getOutputName() { return outputName; }
    public SelenideElement getOutputEmail() { return outputEmail; }
    public SelenideElement getOutputCurrentAddress() { return outputCurrentAddress; }
    public SelenideElement getOutputPermanentAddress() { return outputPermanentAddress; }
    public SelenideElement getOutput() { return output; }
}
