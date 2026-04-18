package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DatePickerPage {

    private final SelenideElement datePickerInput = $("#datePickerMonthYearInput"),
            dateAndTimePickerInput = $("#dateAndTimePickerInput"),
            datePickerMonth = $x("//select[@class='react-datepicker__month-select']"),
            datePickerYear = $x("//select[@class='react-datepicker__year-select']"),
            dateAndTimePickerCurrentMonth = $x("//h2[contains(@class, 'react-datepicker__current-month')]"),
            dateAndTimePickerNextMonthBtn = $x("//button[@aria-label='Next Month']"),
            dateAndTimePickerPreviousMonthBtn = $x("//button[@aria-label='Previous Month']"),
            dateAndTimePickerTime = $x("//ul[@class='react-datepicker__time-list']");

    public String getDatePickerValue() {
        String s = datePickerInput.getValue();
        return s;
    }

    public String getDateAndTimePickerValue() {
        String s = dateAndTimePickerInput.getValue();
        return s;
    }

    public DatePickerPage setDate(String date) {

        String[] s = date.split("/");

        int dayInt = Integer.parseInt(s[1]);
        String day = String.valueOf(dayInt);

        int monthInt = Integer.parseInt(s[0]);
        String month = String.valueOf(monthInt - 1);
        String year = s[2];

        HashMap<String, String> map = new HashMap<>();
        map.put("0", "January");
        map.put("1", "February");
        map.put("2", "March");
        map.put("3", "April");
        map.put("4", "May");
        map.put("5", "June");
        map.put("6", "July");
        map.put("7", "August");
        map.put("8", "September");
        map.put("9", "October");
        map.put("10", "November");
        map.put("11", "December");

        datePickerInput.click();
        datePickerMonth.selectOptionByValue(month);
        datePickerYear.selectOptionByValue(year);
        $x("//div[contains(@class,'react-datepicker__day')][contains(@aria-label, '" + map.get(month) + " " + day + "')]").click();

        return this;
    }

    public DatePickerPage setDateAndTime(String date) {

        String[] s = date.split("[ ,]+");

        int dayInt = Integer.parseInt(s[1]);
        String day = String.valueOf(dayInt);

        String month = s[0];

        String year = s[2];

        String[] times = s[3].split(":");
        StringBuilder sb = new StringBuilder();

        if (times[0].length() == 1) {
            sb.append("0");
            sb.append(times[0]);
            sb.append(":");
            sb.append(times[1]);
        } else {
            sb.append(times[0]);
            sb.append(":");
            sb.append(times[1]);
        }

        String time = sb.toString();

        dateAndTimePickerInput.click();

        while (!dateAndTimePickerCurrentMonth.getText().equals(month + " " + year)) {
            dateAndTimePickerPreviousMonthBtn.click();
        }

        $x("//div[contains(@class,'react-datepicker__day')][contains(@aria-label, '" + month + " " + day + "')]").click();
        dateAndTimePickerTime.find(byText(time)).click();

        return this;
    }

}
