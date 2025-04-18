package kr.ac.dankook.ace.lab3.dto;

import lombok.Data;

@Data
public class TsunamiSearchForm {
    private Integer startYear;
    private Integer endYear;
    private Integer exactValidity;
    private Integer valMin;
    private Integer valMax;
    private Integer exactCode;
    private Integer codeMin;
    private Integer codeMax;
}
