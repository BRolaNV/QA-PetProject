package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class DynamicPropertiesPage {

    private final SelenideElement enableAfter = $("#enableAfter"),
            changeColor = $("#colorChange"),
            visibleAfter = $("#visibleAfter");
}
