package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.MenuPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

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

    @Test
    void menuTest() {

        menuPage.hoverItem1();
        menuPage.getSubList().shouldNotBe(visible);

        menuPage.hoverItem3();
        menuPage.getSubList().shouldNotBe(visible);

        menuPage.hoverItem2();
        menuPage.getSubList().shouldBe(visible);

        menuPage.hoverSubList();
        menuPage.getSubSubList1().shouldBe(visible);
        menuPage.getSubSubList2().shouldBe(visible);

    }
}
