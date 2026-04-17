package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.interactionsPage.ResizablePage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResizableTest extends BaseUITest {

    ResizablePage resizablePage = new ResizablePage();


    @BeforeEach
    void openPage() {
        open("/resizable");
    }

    void resize(int x, int y) {
        SelenideElement holder = resizablePage.getResizeableBox();
        actions().clickAndHold(holder).moveByOffset(x, y).release().perform();
    }

    void assertStyle(String s) {
        String result = resizablePage.getResizeableBoxResult().getAttribute("style");
        assertEquals(s, result);
    }

    @Test
    void resizableBoxPositiveTest() {

        resize(100, 50);
        assertStyle("width: 300px; height: 250px;");
    }

    @Test
    void resizableBoxBellowMinimumTest() {

        resize(-100, -100);
        assertStyle("width: 150px; height: 150px;");
    }

    @Test
    void resizableBoxAboveMaximumTest() {

        resize(350, 150);
        assertStyle("width: 500px; height: 300px;");
    }

    @Test
    void resizableBoxBoundaryTest() {

        resize(-49, -49);
        assertStyle("width: 151px; height: 151px;");
        resize(49, 49);

        resize(-50, -50);
        assertStyle("width: 150px; height: 150px;");
        resize(50, 50);

        resize(-51, -51);
        assertStyle("width: 150px; height: 150px;");
        resize(50, 50);

        resize(299, 99);
        assertStyle("width: 499px; height: 299px;");
        resize(-299, -99);

        resize(300, 200);
        assertStyle("width: 500px; height: 300px;");
        resize(-300, -200);

        resize(301, 201);
        assertStyle("width: 500px; height: 300px;");
        resize(-300, -200);
    }

    @Test
    void resizableTest() {

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
