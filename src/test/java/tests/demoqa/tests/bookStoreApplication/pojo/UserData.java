package tests.demoqa.tests.bookStoreApplication.pojo;

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
