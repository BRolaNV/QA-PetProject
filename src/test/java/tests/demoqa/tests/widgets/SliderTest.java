package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.widgetsPage.SliderPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Slider")
public class SliderTest extends BaseUITest {

    SliderPage sliderPage = new SliderPage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/slider");
    }

    @RetryingTest(3)
    @DisplayName("Move slider and check")
    @Severity(SeverityLevel.NORMAL)
    void sliderTest() {

        String value = "57";
        sliderPage.setSlider(value);
        assertEquals(value, sliderPage.getSliderValue());
    }
}
