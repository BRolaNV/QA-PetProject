package tests.demoqa.tests;
import com.codeborne.selenide.Configuration;
import helpers.AllureAttachmentsExtension;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

@ExtendWith(AllureAttachmentsExtension.class)
public abstract class BaseUITest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";

        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "false"));

        if (isRemote) {
            String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");
            Configuration.remote = gridUrl;

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");

            Configuration.browserCapabilities = options;
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }
}
