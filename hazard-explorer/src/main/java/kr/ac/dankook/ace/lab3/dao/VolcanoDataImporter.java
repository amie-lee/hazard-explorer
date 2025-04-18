package kr.ac.dankook.ace.lab3.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Volcano;

@Component
public class VolcanoDataImporter {

    @Autowired
    private VolcanoParser volcanoParser;

    private List<Volcano> cachedVolcanoes;

    // 파싱한 데이터 로드
    public List<Volcano> load() {
        if (cachedVolcanoes == null) {
            cachedVolcanoes = volcanoParser.parse();
        }
        return cachedVolcanoes;
    }

    // 조회된 리스트 csv 파일로 저장
    public void save(List<Volcano> list) {
        String filePath = "src/main/resources/data/filtered-volcanoes.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Date,Name,Type,Location,Country,Elevation,Latitude,Longitude,VEI");
            for (Volcano v : list) {
                writer.printf("%s,%s,%s,%s,%s,%d,%.5f,%.5f,%d%n",
                    v.getFormatDate(),
                    v.getName(),
                    v.getType(),
                    v.getLocation(),
                    v.getCountry(),
                    v.getElevation(),
                    v.getLatitude(),
                    v.getLongitude(),
                    v.getVei()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
