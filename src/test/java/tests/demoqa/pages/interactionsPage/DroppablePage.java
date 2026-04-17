package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class DroppablePage {

    private final SelenideElement accept = $x("//button[text()='Accept']"),
            preventPropagation = $x("//button[text()='Prevent Propogation']"),
            revertDraggable = $x("//button[text()='Revert Draggable']");

    @Getter
    private final SelenideElement dragMe = $x("//div[@id='draggable']"),
            dropHereSimple = $x("//div[@id='droppable']"),
            simpleResult = $x("//div[@id='simpleDropContainer']//p"),
            acceptable = $x("//div[@id='acceptDropContainer']//div[text()='Acceptable']"),
            notAcceptable = $x("//div[@id='acceptDropContainer']//div[text()='Not Acceptable']"),
            dropHereAccept = $x("//div[@id='acceptDropContainer']//p[text()='Drop here']"),
            acceptResult = $x("//div[@id='acceptDropContainer']//p"),
            dragBox = $x("//div[@id='dragBox']"),
            notGreedy = $x("//div[@id='notGreedyInnerDropBox']"),
            greedy = $x("//div[@id='greedyDropBoxInner']"),
            notGreedyResult = $x("//div[@id='notGreedyDropBox']//p"),
            greedyResult = $x("//div[@id='greedyDropBox']//p"),
            revertable = $x("//div[@id='revertable']"),
            notRevertable = $x("//div[@id='notRevertable']"),
            dropHereRevert = $x("//div[@id='revertableDropContainer']//div[@id='droppable']");


    public DroppablePage moveElementToElement(SelenideElement moveIt, SelenideElement toIt) {
        actions()
                .clickAndHold(moveIt)
                .moveToElement(toIt)
                .release().perform();
        return this;
    }

    public DroppablePage moveElementToLocation(int xCoordinate, int yCoordinate, SelenideElement element) {
        actions()
                .clickAndHold(element)
                .moveToLocation(xCoordinate, yCoordinate)
                .release().perform();
        return this;
    }

    public DroppablePage openAccept() {
        accept.click();
        return this;
    }

    public DroppablePage openPreventPropagation() {
        preventPropagation.click();
        return this;
    }

    public DroppablePage openRevertDraggable() {
        revertDraggable.click();
        return this;
    }

}
