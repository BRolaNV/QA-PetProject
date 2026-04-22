package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.AccordionPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Accordion")
public class AccordionTest extends BaseUITest {

    AccordionPage accordionPage = new AccordionPage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/accordian");
    }

    @Test
    @DisplayName("Check accordion subitems")
    @Severity(SeverityLevel.NORMAL)
    void accordionTest() {

        accordionPage.openWhyDoWeUseIt();
        accordionPage.getResult()
                .shouldHave(text("ed to using 'Content here, content here', making it look like readable English. Many"));

        accordionPage.openWhereDoesItComeFrom();
        accordionPage.getResult()
                .shouldHave(text("g it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney Co"));

        accordionPage.openWhatIsLoremIpsum();
        accordionPage.getResult()
                .shouldHave(text("and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ev"));
    }
}
