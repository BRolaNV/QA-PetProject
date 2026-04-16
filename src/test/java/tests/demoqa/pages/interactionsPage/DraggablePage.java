package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
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

    public DraggablePage moveElementByOffSet(int xOffset, int yOffset, SelenideElement element) {
        actions()
                .clickAndHold(element)
                .moveByOffset(xOffset,yOffset)
                .release().perform();
        return this;
    }

    public DraggablePage moveElementToLocation(int xCoordinate, int yCoordinate, SelenideElement element) {
        actions()
                .clickAndHold(element)
                .moveToLocation(xCoordinate,yCoordinate)
                .release().perform();
        return this;
    }

    public String getDragMeEndPosition() {
        return dragMe.getAttribute("style");
    }

    public DraggablePage openAxisRestricted() {
        axisRestricted.click();
        return this;
    }

    public DraggablePage openContainerRestricted() {
        containerRestricted.click();
        return this;
    }

    public DraggablePage openCursorStyle() {
        cursorStyle.click();
        return this;
    }

    public String getOnlyXEndPosition() {
        return onlyX.getAttribute("style");
    }

    public String getOnlyYEndPosition() {
        return onlyY.getAttribute("style");
    }

}
