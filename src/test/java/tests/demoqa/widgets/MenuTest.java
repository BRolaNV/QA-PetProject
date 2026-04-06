package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MenuTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void menuTest(){


        open("/menu");

        $x("//a[text()='Main Item 1']").hover();
        $x("//a[text()='Sub Item']").shouldNotBe(visible);

        $x("//a[text()='Main Item 3']").hover();
        $x("//a[text()='Sub Item']").shouldNotBe(visible);

        $x("//a[text()='Main Item 2']").hover();
        $x("//a[text()='Sub Item']").shouldBe(visible);

        $x("//a[text()='SUB SUB LIST »']").hover();
        $x("//a[text()='Sub Sub Item 2']").shouldBe(visible);

    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
