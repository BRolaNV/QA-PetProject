package tests.demoqa.elements;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CheckBoxTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void selectAllTest() {
        open("/checkbox");

        $("[aria-label='Select Home']").click();

        $("#result").shouldHave(text("home"));
        $("#result").shouldHave(text("desktop"));
        $("#result").shouldHave(text("office"));
        $("#result").shouldHave(text("excelFile"));
    }

    @Test
    void selectOneTest() {
        open("/checkbox");

        $(".rc-tree-switcher").click();
        $x("//span[@aria-label='Select Documents']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]").click();
        $x("//span[@aria-label='Select WorkSpace']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]").click();
        $("[aria-label='Select React']").click();

        $("#result").shouldHave(text("react"));
    }


    @Test
    void selectAndUnselectTest() {
        open("/checkbox");

        $(".rc-tree-switcher").click();
        $x("//span[@aria-label='Select Documents']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]").click();
        $x("//span[@aria-label='Select WorkSpace']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]").click();
        $("[aria-label='Select React']").click();

        $("#result").shouldHave(text("react"));

        $("[aria-label='Select React']").click();

        $("#result").shouldNotBe(visible);
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
