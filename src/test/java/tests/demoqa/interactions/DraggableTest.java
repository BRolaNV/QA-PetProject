package tests.demoqa.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class DraggableTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void open(){
        Selenide.open("/dragabble");
    }

    @Test
    void simpleTest(){

        SelenideElement draggable = $x("//div[@id='dragBox']");

        draggable.shouldBe(visible);

        actions().clickAndHold(draggable).moveByOffset(200,200).release().perform();

        String endPosition = $x("//div[@id='dragBox']").getAttribute("style");
        assertEquals("position: relative; left: 200px; top: 200px;", endPosition);
    }

    @Test
    void axisRestrictedTest(){

        $x("//button[text()='Axis Restricted']").click();

        SelenideElement onlyX = $x("//div[@id='restrictedX']");
        SelenideElement onlyY = $x("//div[@id='restrictedY']");

        onlyX.shouldBe(visible);
        onlyY.shouldBe(visible);

        actions().clickAndHold(onlyX).moveByOffset(200,200).release().perform();
        actions().clickAndHold(onlyY).moveByOffset(200,200).release().perform();

        String xPosition = $x("//div[@id='restrictedX']").getAttribute("style");
        String yPosition = $x("//div[@id='restrictedY']").getAttribute("style");

        assertEquals("position: relative; left: 200px; top: 0px;", xPosition);
        assertEquals("position: relative; left: 0px; top: 200px;", yPosition);
    }

    @Test
    void containerRestrictedContainerTest(){

        $x("//button[text()='Container Restricted']").click();

        SelenideElement element = $x("//div[@id='containmentWrapper']//div[contains(text(),'contained within the box')]");

        Rectangle con = $("#containmentWrapper").getRect();
        Rectangle el = $x("//div[@id='containmentWrapper']//div[contains(text(),'contained within the box')]").getRect();

        element.shouldBe(visible);

        int x = con.getX() + con.getWidth() - el.getX() - el.getWidth();
        int y = con.getY() + con.getHeight() - el.getY() - el.getHeight();

        actions().clickAndHold(element).moveByOffset(x + 100, y + 100).release().perform();

        Rectangle elAfter = $x("//div[@id='containmentWrapper']//div[contains(text(),'contained within the box')]").getRect();

        assertTrue(con.getX() < elAfter.getX());
        assertTrue(con.getY() < elAfter.getY());
        assertTrue(con.getX() + con.getWidth() > elAfter.getWidth() + elAfter.getX());
        assertTrue(con.getY() + con.getHeight() > elAfter.getHeight() + elAfter.getY());

    }

    @Test
    void containerRestrictedParentTest(){

        $x("//button[text()='Container Restricted']").click();

        SelenideElement element = $x("//span[contains(text(),'contained within my parent')]");

        Rectangle con = $x("//span[contains(text(),'contained within my parent')]/..").getRect();
        Rectangle el = $x("//span[contains(text(),'contained within my parent')]").getRect();

        element.shouldBe(visible);

        int x = con.getX() + con.getWidth() - el.getX() - el.getWidth();
        int y = con.getY() + con.getHeight() - el.getY() - el.getHeight();

        actions().clickAndHold(element).moveByOffset(x + 10, y + 10).release().perform();

        Rectangle elAfter = $x("//span[contains(text(),'contained within my parent')]").getRect();

        assertTrue(con.getX() < elAfter.getX());
        assertTrue(con.getY() < elAfter.getY());
        assertTrue(con.getX() + con.getWidth() > elAfter.getWidth() + elAfter.getX());
        assertTrue(con.getY() + con.getHeight() > elAfter.getHeight() + elAfter.getY());

    }

    @Test
    void cursorStyleTest(){

        $x("//button[text()='Cursor Style']").click();

        SelenideElement center = $x("//div[@id='cursorCenter']");
        SelenideElement topLeft = $x("//div[@id='cursorTopLeft']");
        SelenideElement bottom = $x("//div[@id='cursorBottom']");

        center.shouldBe(visible);

        actions().clickAndHold(center).moveToLocation(400, 300).release().perform();
        actions().clickAndHold(topLeft).moveToLocation(400, 300).release().perform();
        actions().clickAndHold(bottom).moveToLocation(400, 300).release().perform();

        Rectangle centerRec = $x("//div[@id='cursorCenter']").getRect();
        Rectangle topLeftRec = $x("//div[@id='cursorTopLeft']").getRect();
        Rectangle bottomRec = $x("//div[@id='cursorBottom']").getRect();

        Point result = new Point(400, 300);

        Point centerPoint = new Point((int) (Math.round((centerRec.getX() + centerRec.getWidth() / 2) / 100.0) * 100),
                (int) (Math.round((centerRec.getY() + centerRec.getHeight() / 2) / 100.0) * 100));
        Point topLeftPoint = new Point((int)Math.round(topLeftRec.getX() / 100.0) * 100, (int)Math.round(topLeftRec.getY() / 100.0) * 100);


        assertEquals(centerPoint, result);
        assertEquals(topLeftPoint, result);
        assertEquals((int) (Math.round((bottomRec.getY() + bottomRec.getHeight()) / 100.0) * 100), result.getY());
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
