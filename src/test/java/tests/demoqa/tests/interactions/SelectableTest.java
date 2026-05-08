package tests.demoqa.tests.interactions;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.interactionsPage.SelectablePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("DemoQA UI")
@Feature("Interactions")
@Story("Selectable")
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
    @DisplayName("Select element in list")
    @Severity(SeverityLevel.NORMAL)
    void listTest() {

        String element = "Cras justo odio";

        selectablePage.getActiveList().shouldNotBe(visible);
        selectablePage.selectInList(element);
        selectablePage.getActiveList().shouldHave(text(element));
    }

    @RetryingTest(3)
    @DisplayName("Select element in grid")
    @Severity(SeverityLevel.NORMAL)
    void gridTest() {

        String element = "Three";
        selectablePage.openGrid();

        selectablePage.getActiveGrid().shouldNotBe(visible);
        selectablePage.selectInGrid(element);
        selectablePage.getActiveGrid().shouldHave(text(element));
    }
}
