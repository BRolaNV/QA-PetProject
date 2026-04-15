package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.SliderPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SliderTest {

    SliderPage sliderPage = new SliderPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/slider");
    }

    @Test
    void sliderTest() {

        String value = "57";
        sliderPage.setSlider(value);
        assertEquals(value, sliderPage.getSliderValue());
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
