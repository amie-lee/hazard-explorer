package kr.ac.dankook.ace.lab3.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Earthquake;

@Component
public class EarthquakeParser {
    
    public List<Earthquake> parse() {
        List<Earthquake> earthquakes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
        .getResourceAsStream("/data/earthquakes.tsv"), StandardCharsets.UTF_8))) {

            String line;
            int lineNum = 0;

            while ((line = br.readLine()) != null) {
                lineNum++;

                // 첫 행(헤더) 무시
                if (lineNum <= 1) {
                    continue;
                }

                String[] tokens = line.split("\t", -1);

                int year = parseIntSafe(tokens[1]);
                int month = parseIntSafe(tokens[2]);
                int day = parseIntSafe(tokens[3]);
                String location = tokens[9].replaceAll("\"", "");
                double latitude = parseDoubleSafe(tokens[10]);
                double longitude = parseDoubleSafe(tokens[11]);
                int depth = parseIntSafe(tokens[12]);
                double magnitude = parseDoubleSafe(tokens[13]);

                Earthquake earthquake = new Earthquake(
                        year, month, day, location, latitude, longitude, depth, magnitude
                );
                earthquakes.add(earthquake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return earthquakes;
    }

    // 값이 비어있을 때 오류가 발생하는 것을 방지하기 위한 메서드
    private int parseIntSafe(String s) {
        if (s == null || s.isBlank()) return 0;
        return Integer.parseInt(s.trim());
    }
    private double parseDoubleSafe(String s) {
        if (s == null || s.isBlank()) return 0.0;
        return Double.parseDouble(s.trim());
    }
}
