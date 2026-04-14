package tests.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.elementsPage.CheckBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CheckBoxTest {

    CheckBoxPage checkBoxPage = new CheckBoxPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
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

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
