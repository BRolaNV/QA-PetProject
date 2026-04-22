package tests.demoqa.pages.widgetsPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AccordionPage {

    private final SelenideElement
            why_do_we_use_it_Btn = $x("//button[contains(text(), 'Why do we use it?')]"),
            where_does_it_come_from_Btn = $x("//button[contains(text(), 'Where does it come from?')]"),
            what_is_lorem_ipsum_Btn = $x("//button[contains(text(), 'What is Lorem Ipsum')]"),
            result = $x("//div[@class='accordion-collapse collapse show']//p");

    @Step("Open 'Why do we use it?' subitem")
    public AccordionPage openWhyDoWeUseIt() {
        why_do_we_use_it_Btn.click();
        return this;
    }

    @Step("Open 'Where does it come from?' subitem")
    public AccordionPage openWhereDoesItComeFrom() {
        where_does_it_come_from_Btn.click();
        return this;
    }

    @Step("Open 'What is Lorem Ipsum' subitem")
    public AccordionPage openWhatIsLoremIpsum() {
        what_is_lorem_ipsum_Btn.click();
        return this;
    }

    public SelenideElement getResult() {
        return result;
    }
}
