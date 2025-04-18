package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Earthquake;

@Component
public class EarthquakeMagFinder {

    // 특정 진도 범위에 포함되는 지진 필터링
    public List<Earthquake> findByMagRange(List<Earthquake> list, Double magMin, Double magMax) {
        return list.stream()
                .filter(e -> (magMin == null || e.getMagnitude() >= magMin) && (magMax == null || e.getMagnitude() <= magMax))
                .toList();
    }

    // 진도 리스트 추출
    public List<Double> extractMagValues(List<Earthquake> earthquakes) {
        return earthquakes.stream()
                .map(Earthquake::getMagnitude)
                .filter(magnitude -> magnitude >=0)
                .distinct()
                .sorted()
                .toList();
    }
}
