package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class ResizablePage {

    @Getter
    private final SelenideElement resizeableBox = $x("//div[@id='resizableBoxWithRestriction']//span"),
            resizeableBoxResult = $x("//div[@id='resizableBoxWithRestriction']"),
            resizeable = $x("//div[@id='resizable']//span"),
            resizeableResult = $x("//div[@id='resizable']");

    @Step("Move element by offset")
    public ResizablePage moveElementByOffSet(int xOffset, int yOffset, SelenideElement element) {
        actions()
                .moveToElement(element)
                .pause(200)
                .clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release().perform();
        return this;
    }
}
