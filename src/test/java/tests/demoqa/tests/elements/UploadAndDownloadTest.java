package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.UploadAndDownloadPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadTest {

    UploadAndDownloadPage uploadAndDownloadPage = new UploadAndDownloadPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
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

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
