package tests.demoqa.tests.interactions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.SortablePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SortableTest extends BaseUITest {

    SortablePage sortablePage = new SortablePage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/sortable");
    }

    @Test
    void listTest() {
        sortablePage.moveElementToElement(sortablePage.getListSix(), sortablePage.getListThree());
        sortablePage.getListThree().shouldHave(text("Six"));
        sortablePage.getListSix().shouldHave(text("Five"));
    }

    @Test
    void gridTest() {
        sortablePage.openGrid();

        sortablePage.moveElementToElement(sortablePage.getGridSix(), sortablePage.getGridThree());
        sortablePage.getGridThree().shouldHave(text("Six"));
        sortablePage.getGridSix().shouldHave(text("Five"));
    }
}
