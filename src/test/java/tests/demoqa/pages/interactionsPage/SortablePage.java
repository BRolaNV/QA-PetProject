package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class SortablePage {

    private final SelenideElement grid = $x("//button[@id='demo-tab-grid']");

    @Getter
    private final SelenideElement listSix = $x("//div[@data-handler-id='T10']"),
            listThree = $x("//div[@data-handler-id='T4']"),
            gridSix = $x("//div[@data-handler-id='T22']"),
            gridThree = $x("//div[@data-handler-id='T16']");;

    public SortablePage openGrid() {
        grid.click();
        return this;
    }

    public SortablePage moveElementToElement(SelenideElement moveIt, SelenideElement toIt) {
        actions()
                .clickAndHold(moveIt)
                .moveToElement(toIt)
                .release().perform();
        return this;
    }
}
