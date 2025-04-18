package kr.ac.dankook.ace.lab3.dto;

import lombok.Data;

@Data
public class VolcanoSearchForm {
    private Integer startYear;
    private Integer endYear;
    private Integer veiMin;
    private Integer veiMax;
    private Integer exactVei;
    private String type;
}
