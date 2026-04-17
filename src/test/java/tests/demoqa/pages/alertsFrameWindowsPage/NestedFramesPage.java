package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class NestedFramesPage {

    private final SelenideElement
            textOutFrame = $x("//h1[@class='text-center']"),
            textInFrame = $("body");


}
