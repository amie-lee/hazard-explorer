package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Tsunami;

@Component
public class TsunamiDateFinder {

    // 특정 연도 범위에 포함되는 쓰나미 필터링
    public List<Tsunami> findByYearRange(List<Tsunami> list, Integer startYear, Integer endYear) {
        return list.stream()
        .filter(t -> (startYear == null || t.getYear() >= startYear) 
        && (endYear == null || t.getYear() <= endYear))
        .toList();
    }

    // 연도 리스트 추출
    public List<Integer> extractYearValues(List<Tsunami> tsunamis) {
        return tsunamis.stream()
                .map(Tsunami::getYear)
                .distinct()
                .sorted()
                .toList();
    }
}
