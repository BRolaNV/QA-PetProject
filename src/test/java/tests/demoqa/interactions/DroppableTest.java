package tests.demoqa.interactions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import java.awt.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DroppableTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void open(){
        Selenide.open("/droppable");
    }

    @Test
    void simpleTest(){

        SelenideElement draggable = $x("//div[@id='draggable']");
        SelenideElement droppable = $x("//div[@id='droppable']");

        actions().clickAndHold(draggable).moveToElement(droppable).release().perform();

        $x("//div[@id='simpleDropContainer']//p").shouldHave(text("Dropped!"));
    }

    @Test
    void acceptTest(){

        $x("//button[text()='Accept']").click();

        SelenideElement acceptable = $x("//div[@id='acceptDropContainer']//div[text()='Acceptable']");
        SelenideElement notAcceptable = $x("//div[@id='acceptDropContainer']//div[text()='Not Acceptable']");
        SelenideElement droppable = $x("//div[@id='acceptDropContainer']//p[text()='Drop here']");

        acceptable.shouldBe(visible);
        acceptable.getLocation();

        actions().clickAndHold(notAcceptable).moveToElement(droppable).release().perform();
        $x("//div[@id='acceptDropContainer']//p").shouldNotHave(text("Dropped!"));

        actions().clickAndHold(acceptable).moveToElement(droppable).release().perform();
        $x("//div[@id='acceptDropContainer']//p").shouldHave(text("Dropped!"));
    }

    @Test
    void preventPropagationTest(){

        $x("//button[text()='Prevent Propogation']").click();

        SelenideElement dragBox = $x("//div[@id='dragBox']");
        SelenideElement notGreedy = $x("//div[@id='notGreedyInnerDropBox']");
        SelenideElement greedy = $x("//div[@id='greedyDropBoxInner']");

        dragBox.shouldBe(visible);
        dragBox.getLocation();

        actions().clickAndHold(dragBox).moveToElement(notGreedy).release().perform();
        $x("//div[@id='notGreedyDropBox']//p").shouldNotHave(text("Outer droppable"));

        actions().clickAndHold(dragBox).moveToElement(greedy).release().perform();
        $x("//div[@id='greedyDropBox']//p").shouldHave(text("Outer droppable"));
    }

    @Test
    void revertDraggableTest(){

        $x("//button[text()='Revert Draggable']").click();

        SelenideElement revertable = $x("//div[@id='revertable']");
        SelenideElement notRevertable = $x("//div[@id='notRevertable']");
        SelenideElement droppable = $x("//div[@id='revertableDropContainer']//div[@id='droppable']");

        revertable.shouldBe(visible);

        Point revStartLocation = revertable.getLocation();
        Point notRevStartLocation = notRevertable.getLocation();

        actions().clickAndHold(revertable).moveToElement(droppable).release().perform();
        actions().clickAndHold(notRevertable).moveToElement(droppable).release().perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Point revEndLocation = revertable.getLocation();
        Point notRevEndLocation = notRevertable.getLocation();

        assertEquals(revStartLocation, revEndLocation);
        assertNotEquals(notRevStartLocation, notRevEndLocation);

        actions().clickAndHold(notRevertable).moveToLocation(notRevStartLocation.getX(), notRevStartLocation.getY()).release().perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Point notRevLastLocation = notRevertable.getLocation();

        assertEquals(notRevEndLocation, notRevLastLocation);
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
