package tests.demoqa.tests.book_store_application.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserData {

    private String userName;
    private String password;

}
