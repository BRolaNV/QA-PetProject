package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccordionTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void accordionTest(){


        open("/accordian");

        $x("//button[contains(text(), 'Why do we use it?')]").click();
        $x("//div[@class='accordion-collapse collapse show']//p")
                .shouldHave(text("ed to using 'Content here, content here', making it look like readable English. Many"));

        $x("//button[contains(text(), 'Where does it come from?')]").click();
        $x("//div[@class='accordion-collapse collapse show']//p")
                .shouldHave(text("g it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney Co"));

        $x("//button[contains(text(), 'What is Lorem Ipsum')]").click();
        $x("//div[@class='accordion-collapse collapse show']//p")
                .shouldHave(text("and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ev"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
