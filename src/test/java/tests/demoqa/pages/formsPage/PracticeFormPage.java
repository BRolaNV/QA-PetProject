package tests.demoqa.pages.formsPage;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PracticeFormPage {

    private final SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genderMale = $x("//input[@type='radio'][@value='Male']"),
            genderFemale = $x("//input[@type='radio'][@value='Female']"),
            genderOther = $x("//input[@type='radio'][@value='Other']"),
            mobileNumber = $x("//input[@placeholder='Mobile Number']"),
            datePickerContainer = $x("//div[@class='react-datepicker__input-container']"),
            datePickerMonth = $x("//select[@class='react-datepicker__month-select']"),
            datePickerYear = $x("//select[@class='react-datepicker__year-select']"),
            subjectsInput = $("#subjectsInput"),
            subjectsChoose = $x("//div[contains(@class,'subjects-auto-complete__option')]"),
            hobbySports = $("#hobbies-checkbox-1"),
            hobbyReading = $("#hobbies-checkbox-2"),
            hobbyMusic = $("#hobbies-checkbox-3"),
            uploadBtn = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#react-select-3-input"),
            city = $("#react-select-4-input"),
            submitBtn = $x("//button[text()='Submit']"),
            result = $("tbody");

    @Step("Fill first name")
    public PracticeFormPage setFirstName(String s) {
        firstName.setValue(s);
        return this;
    }

    @Step("Fill last name")
    public PracticeFormPage setLastName(String s) {
        lastName.setValue(s);
        return this;
    }

    @Step("Fill email")
    public PracticeFormPage setUserEmail(String s) {
        userEmail.setValue(s);
        return this;
    }

    @Step("Fill gender")
    public PracticeFormPage setGender(String Male_Female_Other) {
        switch (Male_Female_Other.toLowerCase()) {
            case "male":
                genderMale.click();
                break;
            case "female":
                genderFemale.click();
                break;
            case "other":
                genderOther.click();
                break;
        }
        return this;
    }

    @Step("Fill mobile number")
    public PracticeFormPage setMobileNumber(String s) {
        mobileNumber.setValue(s);
        return this;
    }

    @Step("Fill birthday (date format \"dd MMMM,yyyy\", e.g. \"01 January,2001\")")
    public PracticeFormPage setBirthday(String date) {

        String[] s = date.split("[ ,]+");

        int dayInt = Integer.parseInt(s[0]);
        String day = String.valueOf(dayInt);
        String month = s[1];
        String year = s[2];

        HashMap<String, String> map = new HashMap<>();
        map.put("January", "0");
        map.put("February", "1");
        map.put("March", "2");
        map.put("April", "3");
        map.put("May", "4");
        map.put("June", "5");
        map.put("July", "6");
        map.put("August", "7");
        map.put("September", "8");
        map.put("October", "9");
        map.put("November", "10");
        map.put("December", "11");

        datePickerContainer.click();
        datePickerMonth.selectOptionByValue(map.get(month));
        datePickerYear.selectOptionByValue(year);
        $x("//div[contains(@class,'react-datepicker__day')][text()='" + day + "'][1]").click();

        return this;
    }

    @Step("Fill subject")
    public PracticeFormPage setSubject(String s) {
        subjectsInput.setValue(s);
        subjectsChoose.click();
        return this;
    }

    @Step("Select hobby (Sport)")
    public PracticeFormPage selectHobbySport() {
        hobbySports.click();
        return this;
    }

    @Step("Select hobby (Reading)")
    public PracticeFormPage selectHobbyReading() {
        hobbyReading.click();
        return this;
    }

    @Step("Select hobby (Music)")
    public PracticeFormPage selectHobbyMusic() {
        hobbyMusic.click();
        return this;
    }

    @Step("Upload file")
    public PracticeFormPage uploadFile(String path) {
        uploadBtn.uploadFile(new File(path));
        return this;
    }

    @Step("Fill current address")
    public PracticeFormPage selectCurrentAddress(String s) {
        currentAddress.setValue(s);
        return this;
    }

    @Step("Fill state")
    public PracticeFormPage selectState(String s) {
        state.setValue(s);
        $x("//div[text()='" + s + "']").click();
        return this;
    }

    @Step("Fill city")
    public PracticeFormPage selectCity(String s) {
        city.setValue(s);
        $x("//div[text()='" + s + "']").click();
        return this;
    }

    @Step("Submit")
    public PracticeFormPage submit() {
        submitBtn.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Remove subject")
    public PracticeFormPage removeSubject(String s) {
        $x("//div[@aria-label='Remove " + s + "']").click();
        return this;
    }

    public SelenideElement getTable() {
        return result;
    }
}
