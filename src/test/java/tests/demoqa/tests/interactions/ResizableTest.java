package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.ResizablePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResizableTest {

    ResizablePage resizablePage =  new ResizablePage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/resizable");
    }

    void resize(int x, int y){
        SelenideElement holder = resizablePage.getResizeableBox();
        actions().clickAndHold(holder).moveByOffset(x, y).release().perform();
    }

    void equals(String s){
        String result = resizablePage.getResizeableBoxResult().getAttribute("style");
        assertEquals(s, result);
    }

    @Test
    void resizableBoxPositiveTest(){

        resize(100, 50);
        equals("width: 300px; height: 250px;");
    }

    @Test
    void resizableBoxBellowMinimumTest(){

        resize(-100, -100);
        equals("width: 150px; height: 150px;");
    }

    @Test
    void resizableBoxAboveMaximumTest(){

        resize(350, 150);
        equals("width: 500px; height: 300px;");
    }

    @Test
    void resizableBoxBoundaryTest(){

        resize(-49, -49);
        equals("width: 151px; height: 151px;");
        resize(49, 49);

        resize(-50, -50);
        equals("width: 150px; height: 150px;");
        resize(50, 50);

        resize(-51, -51);
        equals("width: 150px; height: 150px;");
        resize(50, 50);

        resize(299, 99);
        equals("width: 499px; height: 299px;");
        resize(-299, -99);

        resize(300, 200);
        equals("width: 500px; height: 300px;");
        resize(-300, -200);

        resize(301, 201);
        equals("width: 500px; height: 300px;");
        resize(-300, -200);
    }

    @Test
    void resizableTest(){

        resizablePage.getResizeable().scrollIntoView(true);
        resizablePage.getResizeable().shouldBe(visible);

        Selenide.sleep(500);

        resizablePage.moveElementByOffSet(-200, -200, resizablePage.getResizeable());

        String result = resizablePage.getResizeableResult().getAttribute("style");
        assertEquals("width: 20px; height: 20px;", result);
    }


    @AfterEach
    void close() {
        closeWebDriver();
    }
}
