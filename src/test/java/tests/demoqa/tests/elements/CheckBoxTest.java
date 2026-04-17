package tests.demoqa.tests.elements;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.CheckBoxPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class CheckBoxTest extends BaseUITest {

    CheckBoxPage checkBoxPage = new CheckBoxPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/checkbox");
    }

    @Test
    void selectAllTest() {

        checkBoxPage.selectHome();

        checkBoxPage.getResult().shouldHave(text("home"))
                .shouldHave(text("desktop"))
                .shouldHave(text("office"))
                .shouldHave(text("excelFile"));
    }

    @Test
    void selectOneTest() {

        checkBoxPage.clickSwitcher().clickDocumentSwitcher().clickWorkSpaceSwitcher().selectReact();
        checkBoxPage.getResult().shouldHave(text("react"));
    }

    @Test
    void selectAndUnselectTest() {

        checkBoxPage.clickSwitcher().clickDocumentSwitcher().clickWorkSpaceSwitcher().selectReact();
        checkBoxPage.getResult().shouldHave(text("react"));

        checkBoxPage.selectReact();
        checkBoxPage.getResult().shouldNotBe(visible);
    }
}
