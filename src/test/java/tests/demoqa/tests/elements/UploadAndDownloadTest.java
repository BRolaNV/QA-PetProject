package tests.demoqa.tests.elements;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.UploadAndDownloadPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadTest extends BaseUITest {

    UploadAndDownloadPage uploadAndDownloadPage = new UploadAndDownloadPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/upload-download");
    }

    @Test
    void downloadTest() {
        assertTrue(uploadAndDownloadPage.getDownloadFileName().startsWith("data:image"));
    }

    @Test
    void uploadTest() {
        uploadAndDownloadPage.uploadFile("./src/test/resources/test.jpg");
        uploadAndDownloadPage.getUploadedFilePath().shouldHave(text("test.jpg"));
    }
}
