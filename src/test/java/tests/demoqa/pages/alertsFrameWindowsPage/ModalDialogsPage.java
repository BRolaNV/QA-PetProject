package tests.demoqa.pages.alertsFrameWindowsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ModalDialogsPage {

    private final SelenideElement textOutModal = $x("//h1[@class='text-center']"),
            textInModal = $x("//div[@class='modal-title h4']"),
            openSmallBtn = $("#showSmallModal"),
            closeSmallBtn = $("#closeSmallModal"),
            openLargeBtn = $("#showLargeModal"),
            closeLargeBtn = $("#closeLargeModal");

    @Step("Open small dialog")
    public ModalDialogsPage openSmallModal() {
        openSmallBtn.click();
        return this;
    }

    @Step("Close small dialog")
    public ModalDialogsPage closeSmallModal() {
        closeSmallBtn.click();
        return this;
    }

    @Step("Open large dialog")
    public ModalDialogsPage openLargeModal() {
        openLargeBtn.click();
        return this;
    }

    @Step("Close large dialog")
    public ModalDialogsPage closeLargeModal() {
        closeLargeBtn.click();
        return this;
    }

    public SelenideElement getTextOutModal() {
        return textOutModal;
    }

    public SelenideElement getTextInModal() {
        return textInModal;
    }

}
