package tests.demoqa.elements;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinksTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void noContentLinkTest() {

        open("/links");
        $("#no-content").click();
        $("#linkResponse").shouldHave(text("204"));
    }

    @Test
    void movedLinkTest() {

        open("/links");
        $("#moved").click();
        $("#linkResponse").shouldHave(text("301"));
    }

    @Test
    void badRequestLinkTest() {

        open("/links");
        $("#bad-request").click();
        $("#linkResponse").shouldHave(text("400"));
    }

    @Test
    void unauthorizedLinkTest() {

        open("/links");
        $("#unauthorized").click();
        $("#linkResponse").shouldHave(text("401"));
    }

    @Test
    void forbiddenLinkTest() {

        open("/links");
        $("#forbidden").click();
        $("#linkResponse").shouldHave(text("403"));
    }

    @Test
    void notFoundLinkTest() {

        open("/links");
        $("#invalid-url").click(ClickOptions.usingJavaScript());
        $("#linkResponse").shouldHave(text("404"));
    }

    @Test
    void homeLinkTest() {

        open("/links");
        $("#simpleLink").click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    void dynamicLinkTest() {

        open("/links");
        $("#dynamicLink").click();
        switchTo().window(1);
        webdriver().shouldHave(url("https://demoqa.com/"));

    }


    @AfterEach
    void close() {
        closeWebDriver();
    }
}
