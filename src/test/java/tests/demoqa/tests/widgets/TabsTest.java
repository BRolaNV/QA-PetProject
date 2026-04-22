package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.TabsPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Tabs")
public class TabsTest extends BaseUITest {

    TabsPage tabsPage = new TabsPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/tabs");
    }

    @Test
    @DisplayName("Check text in tabs")
    @Severity(SeverityLevel.NORMAL)
    void tabsTest() {

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
}
