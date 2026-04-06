package tests.demoqa.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TabsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void tabsTest(){


        open("/tabs");

        $("#demo-tab-use").click();
        $x("//div[@class='fade tab-pane active show']//p")
                .shouldHave(text("ed to using 'Content here, content here', making it look like readable English. Many"));

        $("#demo-tab-origin").click();
        $x("//div[@class='fade tab-pane active show']//p")
                .shouldHave(text("g it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney Co"));

        $("#demo-tab-what").click();
        $x("//div[@class='fade tab-pane active show']//p")
                .shouldHave(text("and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ev"));
    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
