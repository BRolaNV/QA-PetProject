package tests.demoqa.pages.interactionsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;


public class SelectablePage {

    private final SelenideElement grid = $x("//button[@id='demo-tab-grid']");

    @Getter
    private final SelenideElement activeList = $x("//ul[@id='verticalListContainer']//li[contains(@class,'active')]"),
            activeGrid = $x("//div[@id='gridContainer']//li[contains(@class,'active')]");

    @Step("Open 'grid' page")
    public SelectablePage openGrid() {
        grid.click();
        return this;
    }

    @Step("Select element in list: {element}")
    public SelectablePage selectInList(String element) {
        $x("//li[text()='" + element + "']").click();
        return this;
    }

    @Step("Select element in grid: {element}")
    public SelectablePage selectInGrid(String element) {
        $x("//div[@id='gridContainer']//li[text()='" + element + "']").click();
        return this;
    }
}
