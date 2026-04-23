package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import tests.demoqa.pages.elementsPage.BrokenLinksPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("DemoQA UI")
@Feature("Elements")
@Story("Broken Links - Images")
public class BrokenLinksTest extends BaseUITest {

    BrokenLinksPage brokenLinksPage = new BrokenLinksPage();

    @BeforeEach
    void openPage() {
        open("/broken");
    }

    @Test
    @DisplayName("The link redirects to the main page")
    @Severity(SeverityLevel.NORMAL)
    void validLinkTest() {

        brokenLinksPage.validLinkClick();
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    @DisplayName("Broken link returns 500 status code")
    @Severity(SeverityLevel.NORMAL)
    void brokenLinkTest() {

        brokenLinksPage.brokenLinkClick();
        brokenLinksPage.getContent().shouldHave(text("This page returned a 500 status code."));
    }

    @Test
    @DisplayName("Image doesn't have a size")
    @Severity(SeverityLevel.NORMAL)
    void brokenImageTest() {
        assertEquals(0, brokenLinksPage.getBrokenImageWidth());
    }


    @Test
    @Disabled("Demoqa doesn't have a valid image")
    @DisplayName("Valid image has non-zero width")
    @Severity(SeverityLevel.MINOR)
    void validImageTest() {
        assertTrue(brokenLinksPage.getValidImageWidth() > 0);
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
