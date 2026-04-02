package tests.demoqa.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void downloadTest() {

        open("/upload-download");

        String base64 = executeJavaScript(
                "return document.getElementById('downloadButton').getAttribute('href')"
        );
        assertTrue(base64.startsWith("data:image"));
    }

    @Test
    void uploadTest() {

        open("/upload-download");

        $("#uploadFile").uploadFile(new File("./src/test/resources/test.jpg"));
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
