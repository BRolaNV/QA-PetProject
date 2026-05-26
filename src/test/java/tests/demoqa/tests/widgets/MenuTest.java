package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.widgetsPage.MenuPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Menu")
public class MenuTest extends BaseUITest {

    MenuPage menuPage = new MenuPage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/menu");
    }

    @RetryingTest(3)
    @DisplayName("Check menu and all subitems")
    @Severity(SeverityLevel.NORMAL)
    void menuTest() {

        menuPage.hoverItem1();
        menuPage.getSubList().shouldNotBe(visible);

        menuPage.hoverItem3();
        menuPage.getSubList().shouldNotBe(visible);

        menuPage.hoverItem2();
        menuPage.getSubList().shouldBe(visible);

        menuPage.hoverSubItem();
        menuPage.getSubSubItem1().shouldBe(visible);
        menuPage.getSubSubItem2().shouldBe(visible);

    }
}
