package tests.demoqa.tests.alertsFrameWindows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alertsFrameWindowsPage.FramesPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FramesTest extends BaseUITest {

    FramesPage framesPage = new FramesPage();

    @BeforeEach
    public void openPage() {
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
