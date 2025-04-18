package kr.ac.dankook.ace.lab3.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Tsunami;

@Component
public class TsunamiDataImporter {
    
    @Autowired
    TsunamiParser tsunamiParser;

    private List<Tsunami> cachedTsunamis;

    // 파싱한 데이터 로드
    public List<Tsunami> load() {
        if (cachedTsunamis == null) {
            cachedTsunamis = tsunamiParser.parse();
        }
        return cachedTsunamis;
    }

    // 조회된 리스트 csv 파일로 저장
    public void save(List<Tsunami> list) {
        String filePath = "src/main/resources/data/filtered-tsunamis.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Date,Validity,Cause Code,Country,Location,Latitude,Longitude,Max Height,Runups");
            for (Tsunami t : list) {
                writer.printf("%s,%d,%d,%s,%s,%.5f,%.5f,%.2f,%d%n",
                    t.getFormatDate(),
                    t.getValidity(),
                    t.getCode(),
                    t.getCountry(),
                    t.getLocation(),
                    t.getLatitude(),
                    t.getLongitude(),
                    t.getMaxHeight(),
                    t.getRunup()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
