package tests.demoqa.tests.interactions;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.interactionsPage.SortablePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

/**
 * Flaky на этой странице, элементы не всегда успевают прогрузиться,
 * решить паузами, таймаутом получилось не до конца - поэтому retry.
 */

@Epic("DemoQA UI")
@Feature("Interactions")
@Story("Sortable")
@Flaky
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

    @RetryingTest(3)
    @DisplayName("Move element in list and check sortable")
    @Severity(SeverityLevel.NORMAL)
    void listTest() {
        sortablePage.moveElementToElement(sortablePage.getListSix(), sortablePage.getListThree());
        sortablePage.getListThree().shouldHave(text("Six"));
        sortablePage.getListSix().shouldHave(text("Five"));
    }

    @RetryingTest(3)
    @DisplayName("Move element in grid and check sortable")
    @Severity(SeverityLevel.NORMAL)
    void gridTest() {
        sortablePage.openGrid();

        sortablePage.moveElementToElement(sortablePage.getGridSix(), sortablePage.getGridThree());
        sortablePage.getGridThree().shouldHave(text("Six"));
        sortablePage.getGridSix().shouldHave(text("Five"));
    }
}
