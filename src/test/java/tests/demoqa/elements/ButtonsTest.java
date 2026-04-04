package tests.demoqa.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ButtonsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successClicksTest() {

        open("/buttons");

        $("#doubleClickBtn").doubleClick();
        $("#rightClickBtn").contextClick();
        $x("//button[text()='Click Me']").click();

        $("#doubleClickMessage").shouldHave(text("You have done a double click"));
        $("#rightClickMessage").shouldHave(text("You have done a right click"));
        $("#dynamicClickMessage").shouldHave(text("You have done a dynamic click"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
