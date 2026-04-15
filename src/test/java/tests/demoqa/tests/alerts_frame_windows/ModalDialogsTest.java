package tests.demoqa.tests.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.alerts_frame_windows_Page.ModalDialogsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ModalDialogsTest {

    ModalDialogsPage modalDialogsPage = new ModalDialogsPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

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
