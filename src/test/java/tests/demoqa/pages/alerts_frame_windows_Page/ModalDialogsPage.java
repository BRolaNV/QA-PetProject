package tests.demoqa.pages.alerts_frame_windows_Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ModalDialogsPage {

    private final SelenideElement textOutModal = $x("//h1[@class='text-center']"),
            textInModal = $x("//div[@class='modal-title h4']"),
            openSmallBtn = $("#showSmallModal"),
            closeSmallBtn = $("#closeSmallModal"),
            openLargeBtn = $("#showLargeModal"),
            closeLargeBtn = $("#closeLargeModal");

    public ModalDialogsPage openSmallModal() {
        openSmallBtn.click();
        return this;
    }

    public ModalDialogsPage closeSmallModal() {
        closeSmallBtn.click();
        return this;
    }

    public ModalDialogsPage openLargeModal() {
        openLargeBtn.click();
        return this;
    }

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
