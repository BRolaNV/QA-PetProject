package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.LinksPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Links")
public class LinksTest extends BaseUITest {

    LinksPage linksPage = new LinksPage();


    @BeforeEach
    void openPage() {
        open("/links");
    }

    @Test
    @DisplayName("Check status code 204")
    @Severity(SeverityLevel.NORMAL)
    void noContentLinkTest() {

        linksPage.noContentClick();
        linksPage.getResponse().shouldHave(text("204"));
    }

    @Test
    @DisplayName("Check status code 301")
    @Severity(SeverityLevel.NORMAL)
    void movedLinkTest() {

        linksPage.movedClick();
        linksPage.getResponse().shouldHave(text("301"));
    }

    @Test
    @DisplayName("Check status code 400")
    @Severity(SeverityLevel.NORMAL)
    void badRequestLinkTest() {

        linksPage.badRequestClick();
        linksPage.getResponse().shouldHave(text("400"));
    }

    @Test
    @DisplayName("Check status code 401")
    @Severity(SeverityLevel.NORMAL)
    void unauthorizedLinkTest() {

        linksPage.unauthorizedClick();
        linksPage.getResponse().shouldHave(text("401"));
    }

    @Test
    @DisplayName("Check status code 403")
    @Severity(SeverityLevel.NORMAL)
    void forbiddenLinkTest() {

        linksPage.forbiddenClick();
        linksPage.getResponse().shouldHave(text("403"));
    }

    @Test
    @DisplayName("Check status code 404")
    @Severity(SeverityLevel.NORMAL)
    void notFoundLinkTest() {

        linksPage.invalidURLClick();
        linksPage.getResponse().shouldHave(text("404"));
    }

    @Test
    @DisplayName("Check redirect to main page")
    @Severity(SeverityLevel.NORMAL)
    void homeLinkTest() {

        linksPage.simpleLinkClick();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    @DisplayName("Check redirect to main page via dynamic link")
    @Severity(SeverityLevel.NORMAL)
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
