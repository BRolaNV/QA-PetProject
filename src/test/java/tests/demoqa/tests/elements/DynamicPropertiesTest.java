package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.DynamicPropertiesPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

//flaky страница не всегда успевает прогрузиться
public class DynamicPropertiesTest extends BaseUITest {

    DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();

    @BeforeAll
    static void setUpTimeOut() {
        Configuration.timeout = 5100;
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/dynamic-properties");
    }

    @Test
    void willEnableButtonTest() {

        dynamicPropertiesPage.getEnableAfter().shouldNotBe(enabled);
        dynamicPropertiesPage.getEnableAfter().shouldBe(enabled);
    }

    @Test
    void visibleAfterButtonTest() {

        dynamicPropertiesPage.getVisibleAfter().shouldNotBe(visible);
        dynamicPropertiesPage.getVisibleAfter().shouldBe(visible);
    }

    @Test
    void changeColorButtonTest() {

        dynamicPropertiesPage.getChangeColor().shouldNotHave(cssClass("text-danger"));
        dynamicPropertiesPage.getChangeColor().shouldHave(cssClass("text-danger"));
    }
}
