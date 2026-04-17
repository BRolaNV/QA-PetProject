package tests.demoqa.tests.interactions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.SelectablePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelectableTest extends BaseUITest {

    SelectablePage selectablePage = new SelectablePage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/selectable");
    }

    @Test
    void listTest() {

        String element = "Cras justo odio";

        selectablePage.getActiveList().shouldNotBe(visible);
        selectablePage.selectInList(element);
        selectablePage.getActiveList().shouldHave(text(element));
    }

    @Test
    void gridTest() {

        String element = "Three";
        selectablePage.openGrid();

        selectablePage.getActiveGrid().shouldNotBe(visible);
        selectablePage.selectInGrid(element);
        selectablePage.getActiveGrid().shouldHave(text(element));
    }
}
