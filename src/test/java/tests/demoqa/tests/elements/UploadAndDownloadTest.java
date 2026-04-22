package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.UploadAndDownloadPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Upload and Download")
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
    @DisplayName("Download file")
    @Severity(SeverityLevel.NORMAL)
    void downloadTest() {
        assertTrue(uploadAndDownloadPage.getDownloadFileName().startsWith("data:image"));
    }

    @Test
    @DisplayName("Upload file")
    @Severity(SeverityLevel.NORMAL)
    void uploadTest() {
        uploadAndDownloadPage.uploadFile("./src/test/resources/test.jpg");
        uploadAndDownloadPage.getUploadedFilePath().shouldHave(text("test.jpg"));
    }
}
