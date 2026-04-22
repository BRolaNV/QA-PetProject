package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import tests.demoqa.pages.elementsPage.DynamicPropertiesPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Elements")
@Story("Dynamic Properties")
public class DynamicPropertiesTest extends BaseUITest {

    DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/dynamic-properties");
    }

    @Test
    @DisplayName("Button becomes enabled after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void willEnableButtonTest() {

        dynamicPropertiesPage.getEnableAfter().shouldNotBe(enabled);
        dynamicPropertiesPage.getEnableAfter().shouldBe(enabled);
    }

    @Test
    @DisplayName("Button becomes visible after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void visibleAfterButtonTest() {

        dynamicPropertiesPage.getVisibleAfter().shouldNotBe(visible);
        dynamicPropertiesPage.getVisibleAfter().shouldBe(visible);
    }

    @Test
    @DisplayName("Button changes color after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void changeColorButtonTest() {

        dynamicPropertiesPage.getChangeColor().shouldNotHave(cssClass("text-danger"));
        dynamicPropertiesPage.getChangeColor().shouldHave(cssClass("text-danger"));
    }
}
