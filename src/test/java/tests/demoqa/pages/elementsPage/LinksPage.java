package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

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

    public LinksPage noContentClick() {
        noContent.click();
        return this;
    }

    public LinksPage movedClick() {
        moved.click();
        return this;
    }

    public LinksPage badRequestClick() {
        badRequest.click();
        return this;
    }

    public LinksPage unauthorizedClick() {
        unauthorized.click();
        return this;
    }

    public LinksPage forbiddenClick() {
        forbidden.click();
        return this;
    }

    public LinksPage invalidURLClick() {
        invalidURL.click(ClickOptions.usingJavaScript());
        return this;
    }

    public LinksPage dynamicLinkClick() {
        dynamicLink.click();
        return this;
    }

    public LinksPage simpleLinkClick() {
        simpleLink.click();
        return this;
    }

    public SelenideElement getResponse() {
        return linkResponse;
    }
}
