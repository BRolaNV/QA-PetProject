package tests.demoqa.alerts_frame_windows;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class FramesTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void firstFrameTest() {

        open("/frames");
        switchTo().frame(0);
        $("body").shouldHave(text("This is a sample page"));

        switchTo().defaultContent();
        $x("//h1[@class='text-center']").shouldHave(text("Frames"));
    }

    @Test
    void secondFrameTest() {

        open("/frames");
        switchTo().frame(1);
        $("body").shouldHave(text("This is a sample page"));

        switchTo().defaultContent();
        $x("//h1[@class='text-center']").shouldHave(text("Frames"));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
