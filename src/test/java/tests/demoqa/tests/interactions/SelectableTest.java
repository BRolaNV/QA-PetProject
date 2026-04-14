package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelectableTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void listTest(){

        open("/selectable");

        $x("//ul[@id='verticalListContainer']//li[contains(@class,'active')]").shouldNotBe(visible);
        $x("//li[text()='Cras justo odio']").click();
        $x("//ul[@id='verticalListContainer']//li[contains(@class,'active')]").shouldHave(text("Cras justo odio"));
    }

    @Test
    void gridTest(){

        open("/selectable");

        $x("//button[@id='demo-tab-grid']").click();

        $x("//div[@id='gridContainer']//li[contains(@class,'active')]").shouldNotBe(visible);
        $x("//div[@id='gridContainer']//li[text()='Three']").click();
        $x("//div[@id='gridContainer']//li[contains(@class,'active')]").shouldHave(text("Three"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
