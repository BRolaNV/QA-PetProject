package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.SelectMenuPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelectMenuTest {

    SelectMenuPage selectMenuPage = new SelectMenuPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/select-menu");
    }

    @Test
    void selectValueTest(){
        selectMenuPage.selectValue();
        selectMenuPage.getSelectValueResult().shouldHave(text("Group 2, option 2"));
    }

    @Test
    void selectOneTest(){
        selectMenuPage.selectOne();
        selectMenuPage.getSelectOneResult().shouldHave(text("Mr."));
    }

    @Test
    void oldStyleTest(){
        selectMenuPage.oldSelect("3");
        selectMenuPage.getOldSelectResult().shouldHave(text("Yellow"));
    }

    @Test
    void multiSelectTest(){

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
    void standardMultiSelectTest(){
        String car = "Saab";
        selectMenuPage.standardMultiSelect(car);
        selectMenuPage.getStandardMultiSelectResult().shouldHave(text(car));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
