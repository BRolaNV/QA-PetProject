package tests.demoqa.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenLinksTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void validLinkTest() {

        open("/broken");

        $("a[href='http://demoqa.com']").click();
        webdriver().shouldHave(url("https://demoqa.com/"));
    }

    @Test
    void brokenLinkTest() {

        open("/broken");

        $("a[href='http://the-internet.herokuapp.com/status_codes/500']").click();
        $("#content").shouldHave(text("This page returned a 500 status code."));
    }

    @Test
    void brokenImageTest() {

        open("/broken");

        long width = executeJavaScript(
                "return arguments[0].naturalWidth",
                $("img[src='/images/Toolsqa_1.jpg']").toWebElement()
        );

        assertTrue(width == 0);
    }

    /*
    demoqa отдает битые изображения по обеим ссылкам, корректный тест valid Image невозможен
     */

//    @Test
//    void validImageTest() {
//
//        open("/broken");
//
//        Wait().until(driver -> executeJavaScript(
//                "return arguments[0].complete && arguments[0].naturalWidth > 0",
//                $("img[src='/images/Toolsqa.jpg']").toWebElement()
//        ));
//
//        long width = executeJavaScript(
//                "return arguments[0].naturalWidth",
//                $("img[src='/images/Toolsqa.jpg']").toWebElement()
//        );
//
//        assertTrue(width > 0);
//    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
