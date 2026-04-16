package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import tests.demoqa.pages.interactionsPage.DraggablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class DraggableTest {

    DraggablePage draggablePage = new DraggablePage();

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

        int xOffset = 200;
        int yOffset = 200;

        draggablePage.getDragMe().shouldBe(visible);
        draggablePage.moveElementByOffSet(xOffset, yOffset, draggablePage.getDragMe());

        assertEquals("position: relative; left: "+xOffset+"px; top: "+yOffset+"px;",
                draggablePage.getDragMeEndPosition());
    }

    @Test
    void axisRestrictedTest(){

        int xOffset = 200;
        int yOffset = 200;

        draggablePage.openAxisRestricted();

        draggablePage.getOnlyX().shouldBe(visible);
        draggablePage.getOnlyY().shouldBe(visible);

        draggablePage.moveElementByOffSet(xOffset, yOffset, draggablePage.getOnlyX());
        draggablePage.moveElementByOffSet(xOffset, yOffset, draggablePage.getOnlyY());

        assertEquals("position: relative; left: "+xOffset+"px; top: 0px;",
                draggablePage.getOnlyXEndPosition());
        assertEquals("position: relative; left: 0px; top: "+yOffset+"px;",
                draggablePage.getOnlyYEndPosition());
    }

    @Test
    void containerRestrictedContainerTest(){

        draggablePage.openContainerRestricted();
        draggablePage.getContainingInBox().shouldBe(visible);

        Rectangle container = draggablePage.getBox().getRect();
        Rectangle startPosition = draggablePage.getContainingInBox().getRect();

        int x = container.getX() + container.getWidth() - startPosition.getX() - startPosition.getWidth();
        int y = container.getY() + container.getHeight() - startPosition.getY() - startPosition.getHeight();

        draggablePage.moveElementByOffSet(x + 100, y + 100,draggablePage.getContainingInBox());

        Rectangle afterMove = draggablePage.getContainingInBox().getRect();

        assertNotEquals(startPosition.getX(), afterMove.getX());
        assertNotEquals(startPosition.getY(), afterMove.getY());

        assertTrue(container.getX() < afterMove.getX());
        assertTrue(container.getY() < afterMove.getY());
        assertTrue(container.getX() + container.getWidth() > afterMove.getWidth() + afterMove.getX());
        assertTrue(container.getY() + container.getHeight() > afterMove.getHeight() + afterMove.getY());

    }

    @Test
    void containerRestrictedParentTest(){

        draggablePage.openContainerRestricted();
        draggablePage.getContainingInParent().shouldBe(visible);

        Rectangle container = draggablePage.getParent().getRect();
        Rectangle startPosition = draggablePage.getContainingInParent().getRect();

        int x = container.getX() + container.getWidth() - startPosition.getX() - startPosition.getWidth();
        int y = container.getY() + container.getHeight() - startPosition.getY() - startPosition.getHeight();

        draggablePage.moveElementByOffSet(x + 10, y + 10,draggablePage.getContainingInParent());

        Rectangle afterMove = draggablePage.getContainingInParent().getRect();

        assertNotEquals(startPosition.getX(), afterMove.getX());
        assertNotEquals(startPosition.getY(), afterMove.getY());

        assertTrue(container.getX() < afterMove.getX());
        assertTrue(container.getY() < afterMove.getY());
        assertTrue(container.getX() + container.getWidth() > afterMove.getWidth() + afterMove.getX());
        assertTrue(container.getY() + container.getHeight() > afterMove.getHeight() + afterMove.getY());

    }

    @Test
    void cursorStyleTest(){

        int xCoordinate = 400;
        int yCoordinate = 300;

        draggablePage.openCursorStyle();
        draggablePage.getCursorBottom().shouldBe(visible);

        draggablePage.moveElementToLocation(xCoordinate, yCoordinate, draggablePage.getCursorBottom());
        draggablePage.moveElementToLocation(xCoordinate, yCoordinate, draggablePage.getCursorCenter());
        draggablePage.moveElementToLocation(xCoordinate, yCoordinate, draggablePage.getCursorTopLeft());

        Rectangle centerRec = draggablePage.getCursorCenter().getRect();
        Rectangle topLeftRec = draggablePage.getCursorTopLeft().getRect();
        Rectangle bottomRec = draggablePage.getCursorBottom().getRect();

        Point result = new Point(400, 300);

        Point centerPoint = new Point((int) (Math.round((centerRec.getX() + (double) centerRec.getWidth() / 2) / 100.0) * 100),
                (int) (Math.round((centerRec.getY() + (double) centerRec.getHeight() / 2) / 100.0) * 100));

        Point topLeftPoint = new Point((int)Math.round(topLeftRec.getX() / 100.0) * 100, (int)Math.round(topLeftRec.getY() / 100.0) * 100);

        int bottomY = (int) (Math.round((bottomRec.getY() + bottomRec.getHeight()) / 100.0) * 100);

        assertEquals(centerPoint, result);
        assertEquals(topLeftPoint, result);
        assertEquals(bottomY, result.getY());
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
