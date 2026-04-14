package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SortableTest {


    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void listTest(){

        open("/sortable");

        SelenideElement six = $x("//div[@data-handler-id='T10']");
        SelenideElement three = $x("//div[@data-handler-id='T4']");

        actions().clickAndHold(six).moveToElement(three).release().perform();

        $x("//div[@data-handler-id='T4']").shouldHave(text("Six"));
        $x("//div[@data-handler-id='T10']").shouldHave(text("Five"));
    }

    @Test
    void gridTest(){

        open("/sortable");

        $x("//button[@id='demo-tab-grid']").click();

        SelenideElement six = $x("//div[@data-handler-id='T22']");
        SelenideElement three = $x("//div[@data-handler-id='T16']");

        actions().clickAndHold(six).moveToElement(three).release().perform();

        $x("//div[@data-handler-id='T16']").shouldHave(text("Six"));
        $x("//div[@data-handler-id='T22']").shouldHave(text("Five"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
