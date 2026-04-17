package tests.demoqa.tests.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.BrokenLinksPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrokenLinksTest extends BaseUITest {

    BrokenLinksPage brokenLinksPage = new BrokenLinksPage();


    @BeforeEach
    void openPage() {
        open("/broken");
    }

    @Test
    void validLinkTest() {

        brokenLinksPage.validLinkClick();
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    void brokenLinkTest() {

        brokenLinksPage.brokenLinkClick();
        brokenLinksPage.getContent().shouldHave(text("This page returned a 500 status code."));
    }

    @Test
    void brokenImageTest() {

        assertEquals(0, brokenLinksPage.getBrokenImageWidth());
    }

    /*
    demoqa отдает битые изображения по обеим ссылкам, корректный тест valid Image невозможен
     */
//    @Test
//    void validImageTest() {
//        assertTrue(brokenLinksPage.getValidImageWidth() > 0);
//    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
