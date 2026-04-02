package tests.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void tapYesTest() {
        open("/radio-button");

        $("#yesRadio").click();

        $(".mt-3").shouldHave(text("yes"));
    }

    @Test
    void tapImpressiveTest() {
        open("/radio-button");

        $("#impressiveRadio").click();

        $(".mt-3").shouldHave(text("impressive"));
    }

    @Test
    void tapNoTest() {
        open("/radio-button");

        $("#noRadio").shouldBe(disabled);
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
