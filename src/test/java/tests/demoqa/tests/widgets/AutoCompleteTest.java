package tests.demoqa.tests.widgets;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.AutoCompletePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("DemoQA UI")
@Feature("Widgets")
@Story("Auto Complete")
public class AutoCompleteTest extends BaseUITest {

    String color1 = "Red";
    String color2 = "Green";
    String color3 = "Blue";

    AutoCompletePage autoCompletePage = new AutoCompletePage();

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/auto-complete");
    }

    @Test
    @DisplayName("Check multiple fill")
    @Severity(SeverityLevel.NORMAL)
    void multipleTypeTest() {

        autoCompletePage.multipleInput(color1);
        autoCompletePage.multipleInput(color2);
        autoCompletePage.multipleInput(color3);

        autoCompletePage.getResult()
                .shouldHave(text(color1))
                .shouldHave(text(color2))
                .shouldHave(text(color3));
    }

    @Test
    @DisplayName("Check multiple remove")
    @Severity(SeverityLevel.NORMAL)
    void multipleTypeRemoveTest() {

        autoCompletePage.multipleInput(color1);
        autoCompletePage.multipleInput(color2);
        autoCompletePage.multipleInput(color3);

        autoCompletePage.remove(color2);

        autoCompletePage.getResult()
                .shouldHave(text(color1))
                .shouldNotHave(text(color2))
                .shouldHave(text(color3));
    }

    @Test
    @DisplayName("Check single fill")
    @Severity(SeverityLevel.NORMAL)
    void singleTypeTest() {

        autoCompletePage.singleInput(color1);
        autoCompletePage.getSingleValue().shouldHave(text(color1));

        autoCompletePage.singleInput(color2);
        autoCompletePage.getSingleValue().shouldHave(text(color2));
    }


}
