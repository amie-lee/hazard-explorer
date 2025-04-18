package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Volcano;

@Component
public class VolcanoDateFinder {

    // 특정 연도 범위에 포함되는 화산 필터링
    public List<Volcano> findByYearRange(List<Volcano> list, Integer startYear, Integer endYear) {
        return list.stream()
        .filter(v -> (startYear == null || v.getYear() >= startYear) 
        && (endYear == null || v.getYear() <= endYear))
        .toList();
    }

    // 연도 리스트 추출
    public List<Integer> extractYearValues(List<Volcano> volcanoes) {
        return volcanoes.stream()
                .map(Volcano::getYear)
                .distinct()
                .sorted()
                .toList();
    }
}
