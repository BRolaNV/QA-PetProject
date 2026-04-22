package tests.demoqa.tests.alertsFrameWindows;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alertsFrameWindowsPage.NestedFramesPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Epic("DemoQA UI")
@Feature("Alerts, Frame & Windows")
@Story("Nested Frames")
public class NestedFramesTest extends BaseUITest {

    NestedFramesPage nestedFramesPage = new NestedFramesPage();

    @BeforeEach
    void openPage() {
        open("/nestedframes");
    }

    @Test
    @DisplayName("Verify text inside parent and child frames")
    @Severity(SeverityLevel.NORMAL)
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
