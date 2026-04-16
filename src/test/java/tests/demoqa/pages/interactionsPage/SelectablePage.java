package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class SelectablePage {

    private final SelenideElement grid = $x("//button[@id='demo-tab-grid']");

    @Getter
    private final SelenideElement activeList = $x("//ul[@id='verticalListContainer']//li[contains(@class,'active')]"),
            activeGrid = $x("//div[@id='gridContainer']//li[contains(@class,'active')]");

    public SelectablePage openGrid() {
        grid.click();
        return this;
    }

    public SelectablePage selectInList(String element) {
        $x("//li[text()='"+element+"']").click();
        return this;
    }

    public SelectablePage selectInGrid(String element) {
        $x("//div[@id='gridContainer']//li[text()='"+element+"']").click();
        return this;
    }
}
