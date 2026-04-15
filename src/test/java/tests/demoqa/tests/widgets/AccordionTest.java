package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.AccordionPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AccordionTest {

    AccordionPage accordionPage = new AccordionPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/accordian");
    }

    @Test
    void accordionTest(){

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


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
