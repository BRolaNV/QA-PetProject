package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LinksPage {

    private final SelenideElement noContent = $("#no-content"),
            linkResponse = $("#linkResponse"),
            moved = $("#moved"),
            badRequest = $("#bad-request"),
            unauthorized = $("#unauthorized"),
            forbidden = $("#forbidden"),
            invalidURL = $("#invalid-url"),
            dynamicLink = $("#dynamicLink"),
            simpleLink = $("#simpleLink");

    @Step("Click 'no content' link")
    public LinksPage noContentClick() {
        noContent.click();
        return this;
    }

    @Step("Click 'moved' link")
    public LinksPage movedClick() {
        moved.click();
        return this;
    }

    @Step("Click 'bad request' link")
    public LinksPage badRequestClick() {
        badRequest.click();
        return this;
    }

    @Step("Click 'unauthorized' link")
    public LinksPage unauthorizedClick() {
        unauthorized.click();
        return this;
    }

    @Step("Click 'forbidden' link")
    public LinksPage forbiddenClick() {
        forbidden.click();
        return this;
    }

    @Step("Click 'invalid URL' link")
    public LinksPage invalidURLClick() {
        invalidURL.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Click 'dynamic' link")
    public LinksPage dynamicLinkClick() {
        dynamicLink.click();
        return this;
    }

    @Step("Click 'simple' link")
    public LinksPage simpleLinkClick() {
        simpleLink.click();
        return this;
    }

    public SelenideElement getResponse() {
        return linkResponse;
    }
}
