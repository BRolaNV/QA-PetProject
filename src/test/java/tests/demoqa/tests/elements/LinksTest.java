package tests.demoqa.tests.elements;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.LinksPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinksTest {

    LinksPage linksPage =  new LinksPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/links");
    }

    @Test
    void noContentLinkTest() {

        linksPage.noContentClick();
        linksPage.getResponse().shouldHave(text("204"));
    }

    @Test
    void movedLinkTest() {

        linksPage.movedClick();
        linksPage.getResponse().shouldHave(text("301"));
    }

    @Test
    void badRequestLinkTest() {

        linksPage.badRequestClick();
        linksPage.getResponse().shouldHave(text("400"));
    }

    @Test
    void unauthorizedLinkTest() {

        linksPage.unauthorizedClick();
        linksPage.getResponse().shouldHave(text("401"));
    }

    @Test
    void forbiddenLinkTest() {

        linksPage.forbiddenClick();
        linksPage.getResponse().shouldHave(text("403"));
    }

    @Test
    void notFoundLinkTest() {

        linksPage.invalidURLClick();
        linksPage.getResponse().shouldHave(text("404"));
    }

    @Test
    void homeLinkTest() {

        linksPage.simpleLinkClick();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    void dynamicLinkTest() {

        linksPage.dynamicLinkClick();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
