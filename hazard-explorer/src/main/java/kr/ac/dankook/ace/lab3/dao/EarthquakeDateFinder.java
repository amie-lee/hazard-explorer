package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Earthquake;

@Component
public class EarthquakeDateFinder {

    // 특정 연도 범위에 포함되는 지진 필터링
    public List<Earthquake> findByYearRange(List<Earthquake> list, Integer startYear, Integer endYear) {
        return list.stream()
        .filter(e -> (startYear == null || e.getYear() >= startYear) 
        && (endYear == null || e.getYear() <= endYear))
        .toList();
    }

    // 연도 리스트 추출
    public List<Integer> extractYearValues(List<Earthquake> earthquakes) {
        return earthquakes.stream()
                .map(Earthquake::getYear)
                .distinct()
                .sorted()
                .toList();
    }
}
