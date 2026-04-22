package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class SliderPage {

    private final SelenideElement sliderValue = $("#sliderValue");

    public String getSliderValue() {
        return sliderValue.getValue();
    }

    @Step("Move slider to {value}")
    public SliderPage setSlider(String value) {
        executeJavaScript("var slider = document.getElementById('slider');" +
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "nativeInputValueSetter.call(slider, " + value + ");" +
                "slider.dispatchEvent(new Event('input', { bubbles: true }));");
        return this;
    }
}