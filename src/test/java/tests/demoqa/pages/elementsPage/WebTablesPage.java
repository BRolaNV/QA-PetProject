package tests.demoqa.pages.elementsPage;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WebTablesPage {

    private final SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            age = $("#age"),
            salary = $("#salary"),
            department = $("#department"),
            submit = $("#submit"),
            addNewRecordButton = $("#addNewRecordButton"),
            tbody = $("tbody"),
            searchBox = $("#searchBox"),
            editRecord = $("#edit-record-1"),
            nextButton = $x("//button[text()='Next']"),
            previousButton = $x("//button[text()='Previous']"),
            lastButton = $x("//button[text()='Last']"),
            firstButton = $x("//button[text()='First']"),
            selectButton = $("select"),
            navigationBtnResult = $("strong");

    @Step("Add new record")
    public WebTablesPage addNewRecord() {
        addNewRecordButton.click();
        return this;
    }

    @Step("Fill first name")
    public WebTablesPage setFirstName(String s) {
        firstName.setValue(s);
        return this;
    }

    @Step("Fill last name")
    public WebTablesPage setLastName(String s) {
        lastName.setValue(s);
        return this;
    }

    @Step("Fill email")
    public WebTablesPage setUserEmail(String s) {
        userEmail.setValue(s);
        return this;
    }

    @Step("Fill age")
    public WebTablesPage setAge(String s) {
        age.setValue(s);
        return this;
    }

    @Step("Fill salary")
    public WebTablesPage setSalary(String s) {
        salary.setValue(s);
        return this;
    }

    @Step("Fill department")
    public WebTablesPage setDepartment(String s) {
        department.setValue(s);
        return this;
    }

    @Step("Submit")
    public WebTablesPage clickSubmit() {
        submit.click();
        return this;
    }

    @Step("Search")
    public WebTablesPage search(String s) {
        searchBox.setValue(s);
        return this;
    }

    @Step("Edit")
    public WebTablesPage clickEditBtn() {
        editRecord.click();
        return this;
    }

    @Step("Delete")
    public WebTablesPage delete(String s) {
        $x("//td[text()='" + s + "']/following-sibling::td//span[@title='Delete']").click();
        return this;
    }

    @Step("Click 'next' button")
    public WebTablesPage clickNextBtn() {
        nextButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Click 'previous' button")
    public WebTablesPage clickPreviousBtn() {
        previousButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Click 'last' button")
    public WebTablesPage clickLastBtn() {
        lastButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Click 'first' button")
    public WebTablesPage clickFirstBtn() {
        firstButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Select")
    public WebTablesPage select(String s) {
        selectButton.selectOption(s);
        return this;
    }

    public SelenideElement getNavigationBtnResult() {
        return navigationBtnResult;
    }

    public SelenideElement getTable() {
        return tbody;
    }
}
