package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
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

    @RetryingTest(3)
    @DisplayName("Button becomes enabled after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void willEnableButtonTest() {

        dynamicPropertiesPage.getEnableAfter().shouldNotBe(enabled);
        dynamicPropertiesPage.getEnableAfter().shouldBe(enabled);
    }

    @RetryingTest(3)
    @DisplayName("Button becomes visible after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void visibleAfterButtonTest() {

        dynamicPropertiesPage.getVisibleAfter().shouldNotBe(visible);
        dynamicPropertiesPage.getVisibleAfter().shouldBe(visible);
    }

    @RetryingTest(3)
    @DisplayName("Button changes color after 5 seconds")
    @Severity(SeverityLevel.NORMAL)
    void changeColorButtonTest() {

        dynamicPropertiesPage.getChangeColor().shouldNotHave(cssClass("text-danger"));
        dynamicPropertiesPage.getChangeColor().shouldHave(cssClass("text-danger"));
    }
}
