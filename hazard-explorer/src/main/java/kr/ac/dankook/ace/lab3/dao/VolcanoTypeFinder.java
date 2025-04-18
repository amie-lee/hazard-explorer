package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Volcano;

@Component
public class VolcanoTypeFinder {

    // 특정 Type 값과 일치하는 화산 필터링
    public List<Volcano> findByType(List<Volcano> list, String type) {
        return list.stream()
                .filter(v -> v.getType().equals(type))
                .toList();
    }
    
    // Type 리스트 추출
    public List<String> extractTypeValues(List<Volcano> volcanoes) {
        return volcanoes.stream()
                .map(Volcano::getType)
                .distinct()
                .sorted()
                .toList();
    }
}
