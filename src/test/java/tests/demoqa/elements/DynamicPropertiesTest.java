package tests.demoqa.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DynamicPropertiesTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 5100;
    }

    @Test
    void willEnableButtonTest() {

        open("/dynamic-properties");

        $("#enableAfter").shouldNotBe(enabled);
        $("#enableAfter").shouldBe(enabled);
    }

    @Test
    void visibleAfterButtonTest() {

        open("/dynamic-properties");

        $("#visibleAfter").shouldNotBe(visible);
        $("#visibleAfter").shouldBe(visible);
    }

    @Test
    void changeColorButtonTest() {

        open("/dynamic-properties");

        $("#colorChange").shouldNotHave(cssClass("text-danger"));
        $("#colorChange").shouldHave(cssClass("text-danger"));
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
