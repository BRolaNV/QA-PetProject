package tests.demoqa.pages.alerts_frame_windows_Page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class FramesPage {

    private final SelenideElement
            textOutFrame = $x("//h1[@class='text-center']"),
            textInFrame = $("body");

}
