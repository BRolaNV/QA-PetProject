package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.MenuPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MenuTest {

    MenuPage menuPage =  new MenuPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/menu");
    }

    @Test
    void menuTest(){

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


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
