package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.demoqa.pages.widgetsPage.DatePickerPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatePickerTest {

    DatePickerPage datePickerPage = new DatePickerPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openPage(){
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


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
