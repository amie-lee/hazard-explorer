package kr.ac.dankook.ace.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tsunami {
    private int year;
    private int month;
    private int day;
    private int validity;
    private int code;
    private String country;
    private String location;
    private double latitude;
    private double longitude;
    private double maxHeight;
    private int runup;

    public String getFormatDate() {
        return String.format("%04d.%02d.%02d", year, month, day);
    }
}
