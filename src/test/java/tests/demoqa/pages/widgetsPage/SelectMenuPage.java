package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectMenuPage {

    private final SelenideElement selectValueField = $("#withOptGroup"),
            selectValueChoose = $("#react-select-2-option-1-1"),
            selectOneField = $("#selectOne"),
            selectOneChoose = $("#react-select-3-option-0-1"),
            oldSelectMenu = $("#oldSelectMenu"),
            standardMultiSelect = $("#cars"),
            multiSelectField = $x("//span[@id='react-select-4-live-region']/..//div[@class='css-1xc3v61-indicatorContainer']"),
            multiSelectGreen = $("#react-select-4-option-0"),
            multiSelectBlue = $("#react-select-4-option-1"),
            multiSelectBlack = $("#react-select-4-option-2"),
            multiSelectRed = $("#react-select-4-option-3"),
            multiSelectListbox = $("#react-select-4-listbox"),
            multiSelectRemoveGreenBtn = $x("//div[@aria-label='Remove Green']"),
            multiSelectPlaceholder = $("#react-select-4-placeholder"),
            multiSelectRemoveAllBtn = $x("(//span[@id='react-select-4-live-region']/..//div[@class='css-15lsz6c-indicatorContainer'])[1]");

    public SelectMenuPage selectValue() {
        selectValueField.click();
        selectValueChoose.click();
        return this;
    }

    public SelenideElement getSelectValueResult() {
        return selectValueField;
    }

    public SelectMenuPage selectOne() {
        selectOneField.click();
        selectOneChoose.click();
        return this;
    }

    public SelenideElement getSelectOneResult() {
        return selectOneField;
    }

    public SelectMenuPage oldSelect(String value) {
        oldSelectMenu.selectOptionByValue(value);
        return this;
    }

    public SelenideElement getOldSelectResult() {
        return oldSelectMenu.getSelectedOption();
    }

    public SelectMenuPage standardMultiSelect(String value) {
        standardMultiSelect.selectOptionByValue(value.toLowerCase());
        return this;
    }

    public SelenideElement getStandardMultiSelectResult() {
        return standardMultiSelect.getSelectedOption();
    }

    public SelectMenuPage multiSelectGreen() {
        multiSelectField.click();
        multiSelectGreen.click();
        return this;
    }

    public SelenideElement getMultiSelectListbox() {
        return multiSelectListbox;
    }

    public SelectMenuPage multiSelectRemoveGreen() {
        multiSelectRemoveGreenBtn.click();
        return this;
    }

    public SelenideElement getMultiSelectPlaceholder() {
        return multiSelectPlaceholder;
    }

    public SelectMenuPage multiSelectAll() {
        multiSelectGreen.click();
        multiSelectBlue.click();
        multiSelectBlack.click();
        multiSelectRed.click();
        return this;
    }

    public SelectMenuPage multiSelectRemoveAll() {
        multiSelectRemoveAllBtn.click();
        return this;
    }
}
