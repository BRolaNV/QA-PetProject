package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SliderTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void sliderTest() {

        String value = "57";

        open("/slider");

        executeJavaScript("var slider = document.getElementById('slider');" +
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "nativeInputValueSetter.call(slider, " + value + ");" +
                "slider.dispatchEvent(new Event('input', { bubbles: true }));");

        assertEquals(value, $("#sliderValue").getValue());
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
