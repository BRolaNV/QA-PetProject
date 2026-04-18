package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.SliderPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void sliderTest() {

        //Flaky не всегда страница успевает прогрузится
        String value = "57";
        sliderPage.setSlider(value);
        assertEquals(value, sliderPage.getSliderValue());
    }
}
