package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class ResizablePage {

    @Getter
    private final SelenideElement resizeableBox = $x("//div[@id='resizableBoxWithRestriction']//span"),
            resizeableBoxResult = $x("//div[@id='resizableBoxWithRestriction']"),
            resizeable = $x("//div[@id='resizable']//span"),
            resizeableResult = $x("//div[@id='resizable']");

    public ResizablePage moveElementByOffSet(int xOffset, int yOffset, SelenideElement element) {
        actions()
                .clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release().perform();
        return this;
    }
}
