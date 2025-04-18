package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Tsunami;

@Component
public class TsunamiValidityFinder {

    // 특정 Validity 범위에 포함되는 쓰나미 필터링
    public List<Tsunami> findByValidityRange(List<Tsunami> list, Integer valMin, Integer valMax) {
        return list.stream()
                .filter(t -> (valMin == null || t.getValidity() >= valMin) && (valMax == null || t.getValidity() <= valMax))
                .toList();
    }

    // Validity 리스트 추출
    public List<Tsunami> findByValidity(List<Tsunami> list, Integer validity) {
        return list.stream()
                .filter(t -> t.getValidity() == validity)
                .toList();
    }

    public List<Integer> extractValidityValues(List<Tsunami> tsunamis) {
        return tsunamis.stream()
                .map(Tsunami::getValidity)
                .filter(validity -> validity >= 0)
                .distinct()
                .sorted()
                .toList();
    }
}
