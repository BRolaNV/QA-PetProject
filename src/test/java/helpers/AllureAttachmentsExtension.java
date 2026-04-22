package helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureAttachmentsExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean failed = context.getExecutionException().isPresent();
        if (failed && WebDriverRunner.hasWebDriverStarted()) {
            screenshotAsBytes();
            pageSource();
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] screenshotAsBytes() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public String pageSource() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }
}