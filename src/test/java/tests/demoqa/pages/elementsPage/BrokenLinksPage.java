package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BrokenLinksPage {

    private final SelenideElement
            validLink = $("a[href='http://demoqa.com']"),
            brokenLink = $("a[href='http://the-internet.herokuapp.com/status_codes/500']"),
            content = $("#content"),
            brokenImage = $("img[src='/images/Toolsqa_1.jpg']"),
            validImage = $("img[src='/images/Toolsqa.jpg']");

    @Step("Open main page")
    public BrokenLinksPage validLinkClick() {
        validLink.click();
        return this;
    }

    @Step("Open page with status code 500")
    public BrokenLinksPage brokenLinkClick() {
        brokenLink.click();
        return this;
    }

    public SelenideElement getContent() {
        return content;
    }

    public Long getBrokenImageWidth() {
        long width = executeJavaScript(
                "return arguments[0].naturalWidth",
                brokenImage.toWebElement()
        );

        return width;
    }

    public Long getValidImageWidth() {
        long width = executeJavaScript(
                "return arguments[0].naturalWidth",
                validImage.toWebElement()
        );

        return width;
    }

}
