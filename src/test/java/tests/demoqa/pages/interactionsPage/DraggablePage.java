package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

public class DraggablePage {

    private final SelenideElement axisRestricted = $x("//button[text()='Axis Restricted']"),
            containerRestricted = $x("//button[text()='Container Restricted']"),
            cursorStyle = $x("//button[text()='Cursor Style']");

    @Getter
    private final SelenideElement dragMe = $x("//div[@id='dragBox']"),
            onlyX = $x("//div[@id='restrictedX']"),
            onlyY = $x("//div[@id='restrictedY']"),
            containingInBox = $x("//div[@id='containmentWrapper']//div[contains(text(),'contained within the box')]"),
            box = $("#containmentWrapper"),
            containingInParent = $x("//span[contains(text(),'contained within my parent')]"),
            parent = $x("//span[contains(text(),'contained within my parent')]/.."),
            cursorCenter = $x("//div[@id='cursorCenter']"),
            cursorTopLeft = $x("//div[@id='cursorTopLeft']"),
            cursorBottom = $x("//div[@id='cursorBottom']");

    @Step("Move element by offset")
    public DraggablePage moveElementByOffSet(int xOffset, int yOffset, SelenideElement element) {
        actions()
                .moveToElement(element)
                .pause(200)
                .clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release().perform();
        return this;
    }

    @Step("Move element to location")
    public DraggablePage moveElementToLocation(int xCoordinate, int yCoordinate, SelenideElement element) {
        actions()
                .moveToElement(element)
                .pause(200)
                .clickAndHold(element)
                .moveToLocation(xCoordinate, yCoordinate)
                .release().perform();
        return this;
    }

    @Step("Open axis restricted page")
    public DraggablePage openAxisRestricted() {
        axisRestricted.click();
        return this;
    }

    @Step("Open container restricted page")
    public DraggablePage openContainerRestricted() {
        containerRestricted.click();
        return this;
    }

    @Step("Open cursor style page")
    public DraggablePage openCursorStyle() {
        cursorStyle.click();
        return this;
    }

    public String getDragMeEndPosition() {
        return dragMe.getAttribute("style");
    }

    public String getOnlyXEndPosition() {
        return onlyX.getAttribute("style");
    }

    public String getOnlyYEndPosition() {
        return onlyY.getAttribute("style");
    }

}
