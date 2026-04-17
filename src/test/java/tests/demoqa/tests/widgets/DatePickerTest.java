package tests.demoqa.tests.widgets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.DatePickerPage;
import tests.demoqa.tests.BaseUITest;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatePickerTest extends BaseUITest {

    DatePickerPage datePickerPage = new DatePickerPage();


    @AfterAll
    static void close() {
        closeWebDriver();
    }

    @BeforeEach
    void openPage() {
        open("/date-picker");
    }

    @Test
    void selectDateTest() {

        String date = "01/31/2001";

        datePickerPage.setDate(date);
        assertEquals(date, datePickerPage.getDatePickerValue());

    }

    @Test
    void selectDateAndTimeTest() {

        String date = "February 15, 2025 5:15 AM";
        datePickerPage.setDateAndTime(date);
        assertEquals(date, datePickerPage.getDateAndTimePickerValue());

    }
}
