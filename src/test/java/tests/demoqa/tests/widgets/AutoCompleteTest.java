package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.AutoCompletePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class AutoCompleteTest {

    String color1 = "Red";
    String color2 = "Green";
    String color3 = "Blue";

    AutoCompletePage autoCompletePage =  new AutoCompletePage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage() {
        open("/auto-complete");
    }

    @Test
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
    void singleTypeTest() {

        autoCompletePage.singleInput(color1);
        autoCompletePage.getSingleValue().shouldHave(text(color1));

        autoCompletePage.singleInput(color2);
        autoCompletePage.getSingleValue().shouldHave(text(color2));
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }


}
