package tests.demoqa.alerts_frame_windows;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class ModalDialogsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void smallModalTest() {

        open("/modal-dialogs");

        $("#showSmallModal").click();
        $x("//div[@class='modal-title h4']").shouldHave(text("Small Modal"));

        $("#closeSmallModal").click();
        $x("//h1[@class='text-center']").shouldHave(text("Modal Dialogs"));

    }

    @Test
    void largeModalTest() {

        open("/modal-dialogs");

        $("#showLargeModal").click();
        $x("//div[@class='modal-title h4']").shouldHave(text("Large Modal"));

        $("#closeLargeModal").click();
        $x("//h1[@class='text-center']").shouldHave(text("Modal Dialogs"));

    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
