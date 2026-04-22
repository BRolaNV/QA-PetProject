package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.SelectMenuPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Select Menu")
public class SelectMenuTest extends BaseUITest {

    SelectMenuPage selectMenuPage = new SelectMenuPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/select-menu");
    }

    @Test
    @DisplayName("Select value")
    @Severity(SeverityLevel.NORMAL)
    void selectValueTest() {
        selectMenuPage.selectValue();
        selectMenuPage.getSelectValueResult().shouldHave(text("Group 2, option 2"));
    }

    @Test
    @DisplayName("Select one")
    @Severity(SeverityLevel.NORMAL)
    void selectOneTest() {
        selectMenuPage.selectOne();
        selectMenuPage.getSelectOneResult().shouldHave(text("Mr."));
    }

    @Test
    @DisplayName("Old Style Select Menu")
    @Severity(SeverityLevel.NORMAL)
    void oldStyleTest() {
        selectMenuPage.oldSelect("3");
        selectMenuPage.getOldSelectResult().shouldHave(text("Yellow"));
    }

    @Test
    @DisplayName("Multiselect drop down")
    @Severity(SeverityLevel.NORMAL)
    void multiSelectTest() {

        selectMenuPage.multiSelectGreen();
        selectMenuPage.getMultiSelectListbox().shouldNotHave(text("Green"));

        selectMenuPage.multiSelectRemoveGreen();
        selectMenuPage.getMultiSelectListbox().shouldHave(text("Green"));
        selectMenuPage.getMultiSelectPlaceholder().shouldHave(text("Select..."));

        selectMenuPage.multiSelectAll();
        selectMenuPage.getMultiSelectListbox().shouldHave(text("No options"));

        selectMenuPage.multiSelectRemoveAll();
        selectMenuPage.getMultiSelectPlaceholder().shouldHave(text("Select..."));
    }

    @Test
    @DisplayName("Standard multi select")
    @Severity(SeverityLevel.NORMAL)
    void standardMultiSelectTest() {
        String car = "Saab";
        selectMenuPage.standardMultiSelect(car);
        selectMenuPage.getStandardMultiSelectResult().shouldHave(text(car));
    }
}
