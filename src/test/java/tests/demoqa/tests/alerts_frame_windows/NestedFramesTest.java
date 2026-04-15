package tests.demoqa.tests.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alerts_frame_windows_Page.NestedFramesPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NestedFramesTest {

    NestedFramesPage nestedFramesPage = new NestedFramesPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/nestedframes");
    }

    @Test
    void nestedFrameTest() {

        switchTo().frame(0);
        nestedFramesPage.getTextInFrame().shouldHave(text("Parent frame"));

        switchTo().frame(0);
        nestedFramesPage.getTextInFrame().shouldHave(text("Child Iframe"));

        switchTo().parentFrame();
        switchTo().defaultContent();

        nestedFramesPage.getTextOutFrame().shouldHave(text("Nested Frames"));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
