package tests.demoqa.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PracticeFormData {

    private String firstName;
    private String lastName;
    private String gender;
    private String number;
}
