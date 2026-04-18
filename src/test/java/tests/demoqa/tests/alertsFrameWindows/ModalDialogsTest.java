package tests.demoqa.tests.alertsFrameWindows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alertsFrameWindowsPage.ModalDialogsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ModalDialogsTest extends BaseUITest {

    ModalDialogsPage modalDialogsPage = new ModalDialogsPage();

    @BeforeEach
    void openPage() {
        open("/modal-dialogs");
    }

    @Test
    void smallModalTest() {

        modalDialogsPage.openSmallModal();
        modalDialogsPage.getTextInModal().shouldHave(text("Small Modal"));

        modalDialogsPage.closeSmallModal();
        modalDialogsPage.getTextOutModal().shouldHave(text("Modal Dialogs"));

    }

    @Test
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
