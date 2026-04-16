package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.SelectablePage;
import tests.demoqa.pages.interactionsPage.SortablePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SortableTest {

    SortablePage sortablePage = new SortablePage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/sortable");
    }

    @Test
    void listTest(){
        sortablePage.moveElementToElement(sortablePage.getListSix(), sortablePage.getListThree());
        sortablePage.getListThree().shouldHave(text("Six"));
        sortablePage.getListSix().shouldHave(text("Five"));
    }

    @Test
    void gridTest(){
        sortablePage.openGrid();

        sortablePage.moveElementToElement(sortablePage.getGridSix(), sortablePage.getGridThree());
        sortablePage.getGridThree().shouldHave(text("Six"));
        sortablePage.getGridSix().shouldHave(text("Five"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
