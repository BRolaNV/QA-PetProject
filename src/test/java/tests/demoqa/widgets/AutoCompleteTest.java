package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class AutoCompleteTest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void multipleTypeTest() {

        String color1 = "red";
        String color2 = "green";
        String color3 = "blue";

        open("/auto-complete");

        $("#autoCompleteMultipleInput").setValue(color1);
        $x("//div[contains(@class,'auto-complete__option')]").click();

        $("#autoCompleteMultipleInput").setValue(color2);
        $x("//div[contains(@class,'auto-complete__option')]").click();

        $("#autoCompleteMultipleInput").setValue(color3);
        $x("//div[contains(@class,'auto-complete__option')]").click();

        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldHave(text(color1));
        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldHave(text(color2));
        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldHave(text(color3));
    }

    @Test
    void multipleTypeRemoveTest() {

        String color1 = "Red";
        String color2 = "Green";
        String color3 = "Blue";

        open("/auto-complete");

        $("#autoCompleteMultipleInput").setValue(color1);
        $x("//div[contains(@class,'auto-complete__option')]").click();

        $("#autoCompleteMultipleInput").setValue(color2);
        $x("//div[contains(@class,'auto-complete__option')]").click();
        $x("//div[@aria-label='Remove "+color2+"']").click();

        $("#autoCompleteMultipleInput").setValue(color3);
        $x("//div[contains(@class,'auto-complete__option')]").click();

        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldHave(text(color1));
        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldNotHave(text(color2));
        $x("//div[contains(@class,'auto-complete__value-container')][1]").shouldHave(text(color3));
    }

    @Test
    void singleTypeTest() {

        String color1 = "Red";
        String color2 = "Green";

        open("/auto-complete");

        $("#autoCompleteSingleInput").setValue(color1);
        $x("//div[contains(@class,'auto-complete__option')]").click();
        $x("//div[contains(@class,'auto-complete__single-value')]").shouldHave(text(color1));

        $("#autoCompleteSingleInput").setValue(color2);
        $x("//div[contains(@class,'auto-complete__option')]").click();
        $x("//div[contains(@class,'auto-complete__single-value')]").shouldHave(text(color2));
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
