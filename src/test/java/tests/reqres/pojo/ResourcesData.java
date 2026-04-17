package tests.reqres.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class ResourcesData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
