package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.SelectablePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelectableTest {

    SelectablePage selectablePage = new SelectablePage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/selectable");
    }

    @Test
    void listTest(){

        String element = "Cras justo odio";

        selectablePage.getActiveList().shouldNotBe(visible);
        selectablePage.selectInList(element);
        selectablePage.getActiveList().shouldHave(text(element));
    }

    @Test
    void gridTest(){

        String element = "Three";
        selectablePage.openGrid();

        selectablePage.getActiveGrid().shouldNotBe(visible);
        selectablePage.selectInGrid(element);
        selectablePage.getActiveGrid().shouldHave(text(element));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
