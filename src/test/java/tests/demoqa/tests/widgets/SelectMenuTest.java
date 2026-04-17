package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.SelectMenuPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

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
    void selectValueTest() {
        selectMenuPage.selectValue();
        selectMenuPage.getSelectValueResult().shouldHave(text("Group 2, option 2"));
    }

    @Test
    void selectOneTest() {
        selectMenuPage.selectOne();
        selectMenuPage.getSelectOneResult().shouldHave(text("Mr."));
    }

    @Test
    void oldStyleTest() {
        selectMenuPage.oldSelect("3");
        selectMenuPage.getOldSelectResult().shouldHave(text("Yellow"));
    }

    @Test
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
    void standardMultiSelectTest() {
        String car = "Saab";
        selectMenuPage.standardMultiSelect(car);
        selectMenuPage.getStandardMultiSelectResult().shouldHave(text(car));
    }
}
