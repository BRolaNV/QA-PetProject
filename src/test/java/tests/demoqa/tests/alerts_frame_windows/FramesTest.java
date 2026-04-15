package tests.demoqa.tests.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alerts_frame_windows_Page.FramesPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FramesTest {

    FramesPage framesPage = new FramesPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    public void openPage(){
        open("/frames");
    }

    @Test
    void firstFrameTest() {

        switchTo().frame(0);
        framesPage.getTextInFrame().shouldHave(text("This is a sample page"));

        switchTo().defaultContent();
        framesPage.getTextOutFrame().shouldHave(text("Frames"));
    }

    @Test
    void secondFrameTest() {

        switchTo().frame(1);
        framesPage.getTextInFrame().shouldHave(text("This is a sample page"));

        switchTo().defaultContent();
        framesPage.getTextOutFrame().shouldHave(text("Frames"));
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
