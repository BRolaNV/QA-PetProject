package tests.reqres.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class ForUpdateData {
    private String name;
    private String job;
    private String updatedAt;
}
