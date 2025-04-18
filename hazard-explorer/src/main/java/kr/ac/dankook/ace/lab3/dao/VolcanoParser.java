package kr.ac.dankook.ace.lab3.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Volcano;

@Component
public class VolcanoParser {
    
    public List<Volcano> parse() {
        List<Volcano> volcanoes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
        .getResourceAsStream("/data/volcano-events.tsv"), StandardCharsets.UTF_8))) {
            
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
                String name = tokens[6].replaceAll("\"", "");
                String location = tokens[7].replaceAll("\"", "");
                String country = tokens[8].replaceAll("\"", "");
                double latitude = parseDoubleSafe(tokens[9]);
                double longitude = parseDoubleSafe(tokens[10]);
                int elevation = parseIntSafe(tokens[11]);
                String type = tokens[12].replaceAll("\"", "");
                int vei = parseIntSafe(tokens[13]);

                Volcano volcano = new Volcano(
                        year, month, day, name, location, country,
                        latitude, longitude, elevation, type, vei
                );
                volcanoes.add(volcano);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return volcanoes;
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