package tests.demoqa.tests.alertsFrameWindows;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.alertsFrameWindowsPage.ModalDialogsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("DemoQA UI")
@Feature("Alerts, Frame & Windows")
@Story("Modal Dialogs")
public class ModalDialogsTest extends BaseUITest {

    ModalDialogsPage modalDialogsPage = new ModalDialogsPage();

    @BeforeEach
    void openPage() {
        open("/modal-dialogs");
    }

    @RetryingTest(3)
    @DisplayName("Verify text inside the small modal dialog")
    @Severity(SeverityLevel.NORMAL)
    void smallModalTest() {

        modalDialogsPage.openSmallModal();
        modalDialogsPage.getTextInModal().shouldHave(text("Small Modal"));

        modalDialogsPage.closeSmallModal();
        modalDialogsPage.getTextOutModal().shouldHave(text("Modal Dialogs"));

    }

    @Test
    @DisplayName("Verify text inside the large modal dialog")
    @Severity(SeverityLevel.NORMAL)
    void largeModalTest() {

        modalDialogsPage.openLargeModal();
        modalDialogsPage.getTextInModal().shouldHave(text("Large Modal"));

        modalDialogsPage.closeLargeModal();
        modalDialogsPage.getTextOutModal().shouldHave(text("Modal Dialogs"));

    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
