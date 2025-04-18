package kr.ac.dankook.ace.lab3.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Earthquake;

@Component
public class EarthquakeDataImporter {
    
    @Autowired
    EarthquakeParser earthquakeParser;

    private List<Earthquake> cachedEarthquakes;

    // 파싱한 데이터 로드
    public List<Earthquake> load() {
        if (cachedEarthquakes == null) {
            cachedEarthquakes = earthquakeParser.parse();
        }
        return cachedEarthquakes;
    }

    // 조회된 리스트 csv 파일로 저장
    public void save(List<Earthquake> list) {
        String filePath = "src/main/resources/data/filtered-earthquakes.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Date,Magnitude,Location,Depth(km),Latitude,Longitude");
            for (Earthquake e : list) {
                writer.printf("%s,%.1f,%s,%d,%.5f,%.5f%n",
                    e.getFormatDate(),
                    e.getMagnitude(),
                    e.getLocation(),
                    e.getDepth(),
                    e.getLatitude(),
                    e.getLongitude()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
