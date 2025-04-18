package kr.ac.dankook.ace.lab3.dto;

import lombok.Data;

@Data
public class EarthquakeSearchForm {
    private Integer startYear;
    private Integer endYear;
    private Double magMin;
    private Double magMax;
}
