package tests.demoqa.tests.book_store_application.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BookData {

    public String isbn;
    public String title;
    public String subTitle;
    public String author;
    public String publish_date;
    public String publisher;
    public Integer pages;
    public String description;
    public String website;
}
