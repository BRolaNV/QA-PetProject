package tests.demoqa.tests.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NestedFramesTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void secondFrameTest() {

        open("/nestedframes");
        switchTo().frame(0);
        $("body").shouldHave(text("Parent frame"));

        switchTo().frame(0);
        $("body").shouldHave(text("Child Iframe"));

        switchTo().parentFrame();
        switchTo().defaultContent();

        $x("//h1[@class='text-center']").shouldHave(text("Nested Frames"));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
