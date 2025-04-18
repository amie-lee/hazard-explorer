package kr.ac.dankook.ace.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volcano {
    private int year;
    private int month;
    private int day;
    private String name;
    private String location;
    private String country;
    private double latitude;
    private double longitude;
    private int elevation;
    private String type;
    private int vei;

    public String getFormatDate() {
        return String.format("%04d.%02d.%02d", year, month, day);
    }
}
