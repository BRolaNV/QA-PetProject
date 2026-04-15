package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.TabsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TabsTest {

    TabsPage tabsPage = new TabsPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/tabs");
    }

    @Test
    void tabsTest(){

        tabsPage.openUse()
                .getResult()
                .shouldHave(text("ed to using 'Content here, content here', making it look like readable English. Many"));

        tabsPage.openOrigin()
                .getResult()
                .shouldHave(text("g it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney Co"));

        tabsPage.openWhat()
                .getResult()
                .shouldHave(text("and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ev"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
