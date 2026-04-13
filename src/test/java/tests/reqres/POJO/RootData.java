package tests.reqres.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;


@Getter
@AllArgsConstructor
public class RootData {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private ArrayList<UserData> data;
}
