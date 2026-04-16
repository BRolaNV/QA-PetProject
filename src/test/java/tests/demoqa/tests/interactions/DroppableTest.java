package tests.demoqa.tests.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import tests.demoqa.pages.interactionsPage.DroppablePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DroppableTest {

    DroppablePage droppablePage = new DroppablePage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
        open("/droppable");
    }

    @Test
    void simpleTest(){
        droppablePage.moveElementToElement(droppablePage.getDragMe(), droppablePage.getDropHereSimple());
        droppablePage.getSimpleResult().shouldHave(text("Dropped!"));
    }

    @Test
    void acceptTest(){

        droppablePage.openAccept();

        droppablePage.moveElementToElement(droppablePage.getNotAcceptable(),  droppablePage.getDropHereAccept());
        droppablePage.getAcceptResult().shouldNotHave(text("Dropped!"));

        droppablePage.moveElementToElement(droppablePage.getAcceptable(), droppablePage.getDropHereAccept());
        droppablePage.getAcceptResult().shouldHave(text("Dropped!"));
    }

    @Test
    void preventPropagationTest(){

        droppablePage.openPreventPropagation();

        droppablePage.moveElementToElement(droppablePage.getDragBox(), droppablePage.getNotGreedy());
        droppablePage.getNotGreedyResult().shouldNotHave(text("Outer droppable"));

        droppablePage.moveElementToElement(droppablePage.getDragBox(), droppablePage.getGreedy());
        droppablePage.getGreedyResult().shouldHave(text("Outer droppable"));
    }

    @Test
    void revertDraggableTest(){

        droppablePage.openRevertDraggable();

        Point revStartLocation = droppablePage.getRevertable().getLocation();
        Point notRevStartLocation = droppablePage.getNotRevertable().getLocation();

        droppablePage.moveElementToElement(droppablePage.getRevertable(), droppablePage.getDropHereRevert());
        droppablePage.moveElementToElement(droppablePage.getNotRevertable(), droppablePage.getDropHereRevert());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Point revEndLocation = droppablePage.getRevertable().getLocation();
        Point notRevEndLocation = droppablePage.getNotRevertable().getLocation();

        assertEquals(revStartLocation, revEndLocation);
        assertNotEquals(notRevStartLocation, notRevEndLocation);

        droppablePage.moveElementToLocation(notRevStartLocation.getX(), notRevStartLocation.getY(), droppablePage.getNotRevertable());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Point notRevLastLocation = droppablePage.getNotRevertable().getLocation();

        assertEquals(notRevEndLocation, notRevLastLocation);
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
