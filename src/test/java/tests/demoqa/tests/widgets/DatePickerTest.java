package tests.demoqa.tests.widgets;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatePickerTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void selectDateTest() {

        String date = "01/31/2001";

        open("/date-picker");

        $("#datePickerMonthYearInput").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("0");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("2001");
        $x("//div[contains(@class,'react-datepicker__day')][contains(@aria-label, 'January 31')]").click();

        assertEquals(date,$x("//input[@id='datePickerMonthYearInput']").getValue());

    }

    @Test
    void selectDateAndTimeTest() {

        String date = "February 15, 2025 5:15 AM";

        open("/date-picker");

        $("#dateAndTimePickerInput").click();

        while(!$x("//h2[contains(@class, 'react-datepicker__current-month')]").getText().equals("January 2025")){
            $x("//button[@aria-label='Previous Month']").click();
        }

        $x("//button[@aria-label='Next Month']").click();

        $x("//div[contains(@class,'react-datepicker__day')][contains(@aria-label, 'February 15')]").click();

        $x("//ul[@class='react-datepicker__time-list']").find(byText("05:15")).click();

        assertEquals(date,$x("//input[@id='dateAndTimePickerInput']").getValue());

    }


    @AfterAll
    static void close() {
        closeWebDriver();
    }
}
