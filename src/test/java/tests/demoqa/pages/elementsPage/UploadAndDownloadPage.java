package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class UploadAndDownloadPage {

    private final SelenideElement uploadFileBtn = $("#uploadFile"),
            uploadedFilePath = $("#uploadedFilePath");

    public UploadAndDownloadPage uploadFile(String fileName) {
        uploadFileBtn.uploadFile(new File(fileName));
        return this;
    }

    public SelenideElement getUploadedFilePath() {
        return uploadedFilePath;
    }

    public String getDownloadFileName() {

        String base64 = executeJavaScript(
                "return document.getElementById('downloadButton').getAttribute('href')"
        );

        return base64;
    }
}
