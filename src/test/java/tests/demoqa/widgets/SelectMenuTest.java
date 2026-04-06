package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelectMenuTest {


    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void selectValueTest(){

        open("/select-menu");

        $("#withOptGroup").click();
        $("#react-select-2-option-1-1").click();

        $x("//div[@id='withOptGroup']").shouldHave(text("Group 2, option 2"));
    }

    @Test
    void selectOneTest(){

        open("/select-menu");

        $("#selectOne").click();
        $("#react-select-3-option-0-1").click();

        $("#selectOne").shouldHave(text("Mr."));
    }

    @Test
    void oldStyleTest(){

        open("/select-menu");

        $("#oldSelectMenu").selectOptionByValue("3");
        $("#oldSelectMenu").getSelectedOption().shouldHave(text("Yellow"));
    }

    @Test
    void multiSelectTest(){

        open("/select-menu");

        $x("//span[@id='react-select-4-live-region']/..//div[@class='css-1xc3v61-indicatorContainer']").click();
        $("#react-select-4-option-0").click();
        $("#react-select-4-listbox").shouldNotHave(text("Green"));
        $x("//div[@aria-label='Remove Green']").click();
        $("#react-select-4-listbox").shouldHave(text("Green"));
        $("#react-select-4-placeholder").shouldHave(text("Select..."));
        $("#react-select-4-option-0").click();
        $("#react-select-4-option-1").click();
        $("#react-select-4-option-2").click();
        $("#react-select-4-option-3").click();
        $("#react-select-4-listbox").shouldHave(text("No options"));
        $x("(//span[@id='react-select-4-live-region']/..//div[@class='css-15lsz6c-indicatorContainer'])[1]").click();
        $("#react-select-4-placeholder").shouldHave(text("Select..."));
    }

    @Test
    void standardMultiSelectTest(){

        open("/select-menu");

        $("#cars").selectOptionByValue("saab");
        $("#cars").getSelectedOption().shouldHave(text("Saab"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
