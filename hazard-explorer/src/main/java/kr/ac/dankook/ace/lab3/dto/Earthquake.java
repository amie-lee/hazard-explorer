package kr.ac.dankook.ace.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Earthquake {
    private int year;
    private int month;
    private int day;
    private String location;
    private double latitude;
    private double longitude;
    private int depth;
    private double magnitude;

    public String getFormatDate() {
        return String.format("%04d.%02d.%02d", year, month, day);
    }
}
