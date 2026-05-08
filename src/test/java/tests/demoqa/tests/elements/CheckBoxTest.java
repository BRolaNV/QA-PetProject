package tests.demoqa.tests.elements;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import tests.demoqa.pages.elementsPage.CheckBoxPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("DemoQA UI")
@Feature("Elements")
@Story("Check Box")
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
    @DisplayName("Select all sub-items")
    @Severity(SeverityLevel.NORMAL)
    void selectAllTest() {

        checkBoxPage.selectHome();

        checkBoxPage.getResult().shouldHave(text("home"))
                .shouldHave(text("desktop"))
                .shouldHave(text("office"))
                .shouldHave(text("excelFile"));
    }

    @RetryingTest(3)
    @DisplayName("Select one sub-item")
    @Severity(SeverityLevel.NORMAL)
    void selectOneTest() {

        checkBoxPage.clickSwitcher().clickDocumentSwitcher().clickWorkSpaceSwitcher().selectReact();
        checkBoxPage.getResult().shouldHave(text("react"));
    }

    @Test
    @DisplayName("Select and unselect one sub-item")
    @Severity(SeverityLevel.NORMAL)
    void selectAndUnselectTest() {

        checkBoxPage.clickSwitcher().clickDocumentSwitcher().clickWorkSpaceSwitcher().selectReact();
        checkBoxPage.getResult().shouldHave(text("react"));

        checkBoxPage.selectReact();
        checkBoxPage.getResult().shouldNotBe(visible);
    }
}
